package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.Rol
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SesionViewModel(
    private val repo: AyudaRepository,
    private val preferencias: PreferenciasStore
) : ViewModel() {
    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    private val _perfilActivo = MutableStateFlow("")
    val perfilActivo = _perfilActivo.asStateFlow()
    private val _usuarioId = MutableStateFlow(0)
    val usuarioId = _usuarioId.asStateFlow()
    private val _sesionCargada = MutableStateFlow(false)
    val sesionCargada = _sesionCargada.asStateFlow()
    private val _ingresando = MutableStateFlow(false)
    val ingresando = _ingresando.asStateFlow()
    val modoOscuro = preferencias.modoOscuro.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val notificaciones = preferencias.notificaciones.stateIn(viewModelScope, SharingStarted.Eagerly, true)
    val recibirTickets = preferencias.recibirTickets.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val ticketsUsuario = usuarioActual.flatMapLatest { usuario ->
        if (usuario?.rol == Rol.CLIENTE.name) repo.observarTicketsCliente(usuario.id) else flowOf(emptyList())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun guardarModoOscuro(valor: Boolean) = actualizar { preferencias.guardarModoOscuro(valor) }

    fun guardarNotificaciones(valor: Boolean) = actualizar { preferencias.guardarNotificaciones(valor) }

    fun guardarRecibirTickets(valor: Boolean) {
        if (_usuarioActual.value?.rol == Rol.ADMIN.name) actualizar { preferencias.guardarRecibirTickets(valor) }
    }

    fun cambiarPerfil() {
        val actual = _usuarioActual.value ?: return
        if (_ingresando.value) return
        _ingresando.value = true
        actualizar {
            try {
                val otroRol = if (actual.rol == Rol.CLIENTE.name) Rol.ADMIN else Rol.CLIENTE
                val otro = repo.obtenerUsuarioPorRol(otroRol)
                if (otro == null) {
                    _error.value = "No se encontró el usuario del otro perfil"
                } else {
                    preferencias.guardarSesion(otro.rol, otro.id)
                    _usuarioActual.value = otro
                    _usuarioId.value = otro.id
                    _perfilActivo.value = otro.rol
                }
            } finally {
                _ingresando.value = false
            }
        }
    }

    fun cerrarSesion() {
        if (_ingresando.value) return
        _ingresando.value = true
        actualizar {
            try {
                preferencias.cerrarSesion()
                _usuarioActual.value = null
                _usuarioId.value = 0
                _perfilActivo.value = ""
            } finally {
                _ingresando.value = false
            }
        }
    }

    private fun actualizar(accion: suspend () -> Unit) {
        _error.value = null
        viewModelScope.launch {
            try {
                accion()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _error.value = "No se pudo guardar el cambio. Inténtalo nuevamente"
            }
        }
    }

    init {
        viewModelScope.launch {
            _perfilActivo.value = preferencias.perfilActivo.first()
            _usuarioId.value = preferencias.usuarioId.first()
            if (_usuarioId.value > 0) {
                val usuario = repo.obtenerUsuario(_usuarioId.value)
                if (usuario != null && usuario.rol == _perfilActivo.value) {
                    _usuarioActual.value = usuario
                } else {
                    preferencias.cerrarSesion()
                    _perfilActivo.value = ""
                    _usuarioId.value = 0
                }
            }
            _sesionCargada.value = true
        }
    }

    fun login(correo: String, clave: String, rolSeleccionado: Rol) {
        if (_ingresando.value || !_sesionCargada.value) return
        _error.value = null
        if (correo.isBlank() || clave.isBlank()) {
            _error.value = "Completa el correo y la contraseña"
            return
        }
        _ingresando.value = true
        viewModelScope.launch {
            try {
                val usuario = repo.login(correo.trim(), clave)
                if (usuario == null) {
                    _error.value = "Correo o contraseña incorrectos"
                } else if (usuario.rol != rolSeleccionado.name) {
                    _error.value = "El usuario no corresponde al perfil seleccionado"
                } else {
                    preferencias.guardarSesion(usuario.rol, usuario.id)
                    _usuarioActual.value = usuario
                    _usuarioId.value = usuario.id
                    _perfilActivo.value = usuario.rol
                }
            } finally {
                _ingresando.value = false
            }
        }
    }
}
