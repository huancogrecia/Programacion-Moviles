package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
import com.huanco.ayudacampus_huanco.model.Prioridad
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NuevoTicketViewModel(private val repo: AyudaRepository, private val usuario: Usuario, private val preferencias: PreferenciasStore) : ViewModel() {
    val recibirTickets = preferencias.recibirTickets.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val categorias = repo.observarCategoriasActivas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    private val _categoriaId = MutableStateFlow<Int?>(null)
    val categoriaId = _categoriaId.asStateFlow()
    private val _titulo = MutableStateFlow("")
    val titulo = _titulo.asStateFlow()
    private val _ubicacion = MutableStateFlow("")
    val ubicacion = _ubicacion.asStateFlow()
    private val _prioridad = MutableStateFlow(Prioridad.MEDIA)
    val prioridad = _prioridad.asStateFlow()
    private val _descripcion = MutableStateFlow("")
    val descripcion = _descripcion.asStateFlow()
    private val _errorCategoria = MutableStateFlow<String?>(null)
    val errorCategoria = _errorCategoria.asStateFlow()
    private val _errorTitulo = MutableStateFlow<String?>(null)
    val errorTitulo = _errorTitulo.asStateFlow()
    private val _errorDescripcion = MutableStateFlow<String?>(null)
    val errorDescripcion = _errorDescripcion.asStateFlow()
    private val _errorEnvio = MutableStateFlow<String?>(null)
    val errorEnvio = _errorEnvio.asStateFlow()
    private val _enviando = MutableStateFlow(false)
    val enviando = _enviando.asStateFlow()
    private val _enviado = MutableStateFlow(false)
    val enviado = _enviado.asStateFlow()

    fun seleccionarCategoria(id: Int) {
        _categoriaId.value = id
        _errorCategoria.value = null
    }

    fun cambiarTitulo(texto: String) {
        _titulo.value = texto
        _errorTitulo.value = null
    }

    fun cambiarUbicacion(texto: String) {
        _ubicacion.value = texto
    }

    fun seleccionarPrioridad(valor: Prioridad) {
        _prioridad.value = valor
    }

    fun cambiarDescripcion(texto: String) {
        _descripcion.value = texto
        _errorDescripcion.value = null
    }

    fun enviar() {
        if (_enviando.value || _enviado.value) return
        val titulo = _titulo.value.trim()
        val descripcion = _descripcion.value.trim()
        val ubicacion = _ubicacion.value.trim()
        val categoriaId = _categoriaId.value
        val prioridad = _prioridad.value
        _errorTitulo.value = if (titulo.length < 5) "El título debe tener al menos 5 caracteres" else null
        _errorDescripcion.value = if (descripcion.length < 10) "La descripción debe tener al menos 10 caracteres" else null
        _errorCategoria.value = if (categoriaId == null) "Selecciona una categoría" else null
        _errorEnvio.value = null
        if (_errorTitulo.value != null || _errorDescripcion.value != null || _errorCategoria.value != null) return
        _enviando.value = true
        viewModelScope.launch {
            try {
                if (!preferencias.recibirTickets.first()) {
                    _errorEnvio.value = "Mesa de ayuda cerrada"
                    return@launch
                }
                val activas = repo.observarCategoriasActivas().first()
                if (activas.none { it.id == categoriaId }) {
                    _errorCategoria.value = "Selecciona una categoría activa"
                    return@launch
                }
                repo.crearTicket(usuario.id, requireNotNull(categoriaId), titulo, descripcion, ubicacion, prioridad)
                _enviado.value = true
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorEnvio.value = "No se pudo enviar el ticket. Inténtalo nuevamente"
            } finally {
                _enviando.value = false
            }
        }
    }
}
