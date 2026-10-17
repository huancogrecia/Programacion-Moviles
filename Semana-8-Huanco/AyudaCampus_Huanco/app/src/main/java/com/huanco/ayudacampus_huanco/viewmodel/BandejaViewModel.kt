package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BandejaViewModel(repo: AyudaRepository) : ViewModel() {
    private val _estadoSeleccionado = MutableStateFlow<EstadoTicket?>(null)
    val estadoSeleccionado = _estadoSeleccionado.asStateFlow()

    private val _prioridadSeleccionada = MutableStateFlow<Prioridad?>(null)
    val prioridadSeleccionada = _prioridadSeleccionada.asStateFlow()

    private val _categoriaSeleccionada = MutableStateFlow<Int?>(null)
    val categoriaSeleccionada = _categoriaSeleccionada.asStateFlow()

    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda = _textoBusqueda.asStateFlow()

    val categorias = repo.observarCategorias()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val tickets = combine(
        repo.observarTodosLosTickets(),
        estadoSeleccionado,
        prioridadSeleccionada,
        categoriaSeleccionada,
        combine(textoBusqueda, relojMinutoFlow()) { texto, now -> Pair(texto, now) }
    ) { lista, estado, prioridad, categoriaId, textoNow ->
        val (texto, now) = textoNow
        val busqueda = texto.trim().removePrefix("#")
        lista.filter { detalle ->
            val t = detalle.ticket
            (estado == null || t.estado == estado.name) &&
            (prioridad == null || t.prioridad == prioridad.name) &&
            (categoriaId == null || t.categoriaId == categoriaId) &&
            (busqueda.isEmpty() || t.titulo.contains(busqueda, ignoreCase = true) || t.id.toString().contains(busqueda))
        }.map { TicketUiItem(it, calcularSlaTexto(it.ticket, now)) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun seleccionarEstado(estado: EstadoTicket?) {
        _estadoSeleccionado.value = estado
    }

    fun seleccionarPrioridad(prioridad: Prioridad?) {
        _prioridadSeleccionada.value = prioridad
    }

    fun seleccionarCategoria(categoriaId: Int?) {
        _categoriaSeleccionada.value = categoriaId
    }

    fun buscar(texto: String) {
        _textoBusqueda.value = texto
    }
}
