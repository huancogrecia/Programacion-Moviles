package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TicketDetalleViewModel(
    private val repo: AyudaRepository,
    val usuario: Usuario,
    private val ticketId: Int
) : ViewModel() {
    private val _cargando = MutableStateFlow(true)
    val cargando = _cargando.asStateFlow()
    val ticket = repo.observarTicket(ticketId).onEach { _cargando.value = false }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    private val _textoComentario = MutableStateFlow("")
    val textoComentario = _textoComentario.asStateFlow()
    private val _comentarioFinal = MutableStateFlow("")
    val comentarioFinal = _comentarioFinal.asStateFlow()
    private val _procesando = MutableStateFlow(false)
    val procesando = _procesando.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun cambiarComentario(texto: String) {
        _textoComentario.value = texto
    }

    fun cambiarComentarioFinal(texto: String) {
        _comentarioFinal.value = texto
    }

    fun comentar(texto: String) {
        val comentario = texto.trim()
        if (comentario.isEmpty() || _procesando.value) return
        ejecutar {
            val actual = repo.observarTicket(ticketId).first() ?: return@ejecutar
            if (actual.ticket.clienteId != usuario.id || actual.ticket.estado == EstadoTicket.CERRADO.name) return@ejecutar
            repo.agregarComentario(ticketId, usuario, comentario)
            _textoComentario.value = ""
        }
    }

    fun cerrar(calificacion: Int) {
        if (_procesando.value) return
        if (calificacion !in 1..5) {
            _error.value = "Selecciona una calificación de 1 a 5"
            return
        }
        val final = _comentarioFinal.value.trim()
        ejecutar {
            val actual = repo.observarTicket(ticketId).first() ?: return@ejecutar
            if (actual.ticket.clienteId != usuario.id || actual.ticket.estado != EstadoTicket.RESUELTO.name) return@ejecutar
            if (final.isNotEmpty()) repo.agregarComentario(ticketId, usuario, final)
            repo.cerrarConCalificacion(ticketId, calificacion)
            _comentarioFinal.value = ""
        }
    }

    fun reabrir() {
        if (_procesando.value) return
        ejecutar {
            val actual = repo.observarTicket(ticketId).first() ?: return@ejecutar
            if (actual.ticket.clienteId != usuario.id || actual.ticket.estado != EstadoTicket.RESUELTO.name) return@ejecutar
            repo.reabrir(ticketId)
        }
    }

    private fun ejecutar(accion: suspend () -> Unit) {
        _procesando.value = true
        _error.value = null
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
