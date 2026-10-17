package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.model.Rol
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AtenderViewModel(
    private val repo: AyudaRepository,
    val usuario: Usuario,
    private val ticketId: Int
) : ViewModel() {
    private val _estado = MutableStateFlow(EstadoTicket.ABIERTO)
    val estado = _estado.asStateFlow()
    private val _prioridad = MutableStateFlow(Prioridad.MEDIA)
    val prioridad = _prioridad.asStateFlow()
    private var ultimoEstado: String? = null
    private var ultimaPrioridad: String? = null
    private val _cargando = MutableStateFlow(true)
    val cargando = _cargando.asStateFlow()
    val ticket = repo.observarTicket(ticketId).onEach { detalle ->
        _cargando.value = false
        detalle?.ticket?.let { actual ->
            if (actual.estado != ultimoEstado) _estado.value = EstadoTicket.valueOf(actual.estado)
            if (actual.prioridad != ultimaPrioridad) _prioridad.value = Prioridad.valueOf(actual.prioridad)
            ultimoEstado = actual.estado
            ultimaPrioridad = actual.prioridad
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val slaTexto = combine(ticket, relojMinutoFlow()) { detalle, now ->
        detalle?.ticket?.let { calcularSlaTexto(it, now) } ?: ""
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")
    private val _respuesta = MutableStateFlow("")
    val respuesta = _respuesta.asStateFlow()
    private val _procesando = MutableStateFlow(false)
    val procesando = _procesando.asStateFlow()
    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje = _mensaje.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun cambiarEstado(estado: EstadoTicket) {
        if (_procesando.value || usuario.rol != Rol.ADMIN.name || ticket.value == null ||
            ticket.value?.ticket?.estado == EstadoTicket.CERRADO.name || estado == EstadoTicket.CERRADO) return
        _estado.value = estado
        _mensaje.value = null
    }

    fun cambiarPrioridad(p: Prioridad) {
        if (_procesando.value || usuario.rol != Rol.ADMIN.name || ticket.value == null ||
            ticket.value?.ticket?.estado == EstadoTicket.CERRADO.name) return
        _prioridad.value = p
        _mensaje.value = null
    }

    fun cambiarRespuesta(texto: String) {
        _respuesta.value = texto
    }

    fun responder(texto: String) {
        val respuesta = texto.trim()
        if (respuesta.isEmpty() || _procesando.value || usuario.rol != Rol.ADMIN.name) return
        ejecutar {
            val actual = repo.observarTicket(ticketId).first() ?: return@ejecutar
            if (actual.ticket.estado == EstadoTicket.CERRADO.name) return@ejecutar
            repo.agregarComentario(ticketId, usuario, respuesta)
            _respuesta.value = ""
        }
    }

    fun guardar() {
        if (_procesando.value || usuario.rol != Rol.ADMIN.name) return
        val estado = _estado.value
        val prioridad = _prioridad.value
        if (estado == EstadoTicket.CERRADO) return
        ejecutar {
            val actual = repo.observarTicket(ticketId).first() ?: return@ejecutar
            if (actual.ticket.estado == EstadoTicket.CERRADO.name) return@ejecutar
            if (actual.ticket.prioridad != prioridad.name) repo.cambiarPrioridad(ticketId, prioridad)
            if (actual.ticket.estado != estado.name) repo.cambiarEstado(ticketId, estado)
            _mensaje.value = "Cambios guardados"
        }
    }

    private fun ejecutar(accion: suspend () -> Unit) {
        _procesando.value = true
        _error.value = null
        _mensaje.value = null
        viewModelScope.launch {
            try {
                accion()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _error.value = "No se pudo completar la operación. Inténtalo nuevamente"
            } finally {
                _procesando.value = false
            }
        }
    }
}
