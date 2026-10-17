package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BandejaViewModel(repo: AyudaRepository) : ViewModel() {
    private val _estadoSeleccionado = MutableStateFlow<EstadoTicket?>(null)
    val estadoSeleccionado = _estadoSeleccionado.asStateFlow()
    val tickets = combine(repo.observarTodosLosTickets(), estadoSeleccionado, relojMinutoFlow()) { lista, estado, now ->
        lista.filter { estado == null || it.ticket.estado == estado.name }
            .map { TicketUiItem(it, calcularSlaTexto(it.ticket, now)) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun seleccionarEstado(estado: EstadoTicket?) {
        _estadoSeleccionado.value = estado
    }
}
