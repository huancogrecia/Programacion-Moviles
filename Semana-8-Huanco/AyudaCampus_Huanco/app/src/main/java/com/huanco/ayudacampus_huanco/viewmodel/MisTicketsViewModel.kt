package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MisTicketsViewModel(repo: AyudaRepository, val usuario: Usuario) : ViewModel() {
    private val ticketsUsuario = repo.observarTicketsCliente(usuario.id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    private val _estadoSeleccionado = MutableStateFlow<EstadoTicket?>(null)
    val estadoSeleccionado = _estadoSeleccionado.asStateFlow()
    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda = _textoBusqueda.asStateFlow()

    val tickets = combine(ticketsUsuario, estadoSeleccionado, textoBusqueda) { lista, estado, texto ->
        val busqueda = texto.trim().removePrefix("#")
        lista.filter {
            (estado == null || it.ticket.estado == estado.name) &&
                (busqueda.isEmpty() || it.ticket.titulo.contains(busqueda, ignoreCase = true) ||
                    it.ticket.id.toString().contains(busqueda))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val cantidadAbiertos = ticketsUsuario.map { lista ->
        lista.count { it.ticket.estado == EstadoTicket.ABIERTO.name }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun seleccionarEstado(estado: EstadoTicket?) {
        _estadoSeleccionado.value = estado
    }

    fun buscar(texto: String) {
        _textoBusqueda.value = texto
    }
}
