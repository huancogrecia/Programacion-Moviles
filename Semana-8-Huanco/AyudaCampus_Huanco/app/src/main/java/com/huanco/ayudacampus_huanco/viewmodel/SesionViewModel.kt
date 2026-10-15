package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.Rol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
