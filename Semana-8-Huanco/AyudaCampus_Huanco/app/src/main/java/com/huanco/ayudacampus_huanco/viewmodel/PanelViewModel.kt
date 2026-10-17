package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.model.DatoGrafico
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.ui.theme.EstadoAbierto
import com.huanco.ayudacampus_huanco.ui.theme.EstadoCerrado
import com.huanco.ayudacampus_huanco.ui.theme.EstadoEnProceso
import com.huanco.ayudacampus_huanco.ui.theme.EstadoResuelto
import com.huanco.ayudacampus_huanco.ui.theme.Violeta
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PanelViewModel(repo: AyudaRepository) : ViewModel() {
    val abiertos = repo.observarCantidadPorEstado(EstadoTicket.ABIERTO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val enProceso = repo.observarCantidadPorEstado(EstadoTicket.EN_PROCESO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val resueltos = repo.observarCantidadPorEstado(EstadoTicket.RESUELTO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    private val cerrados = repo.observarCantidadPorEstado(EstadoTicket.CERRADO)
    val porEstado = combine(abiertos, enProceso, resueltos, cerrados) { abiertos, enProceso, resueltos, cerrados ->
        listOf(
            DatoGrafico("Abierto", abiertos.toFloat(), EstadoAbierto),
            DatoGrafico("En proceso", enProceso.toFloat(), EstadoEnProceso),
            DatoGrafico("Resuelto", resueltos.toFloat(), EstadoResuelto),
            DatoGrafico("Cerrado", cerrados.toFloat(), EstadoCerrado)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val porCategoria = repo.observarCantidadPorCategoria().map { conteos ->
        conteos.map { DatoGrafico(it.nombre, it.cantidad.toFloat(), Violeta) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val altasSinAtender = repo.observarAltasSinAtender(System.currentTimeMillis() - 3600 * 1000L)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val tiempoPromedioResolucion = repo.observarTiempoPromedioResolucion().map { promedio ->
        if (promedio == null) {
            "Sin tickets cerrados"
        } else {
            val totalMinutos = (promedio / 60000).toLong()
            val horas = totalMinutos / 60
            val minutos = totalMinutos % 60
            if (horas > 0) "${horas}h ${minutos}min" else "${minutos}min"
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "Sin tickets cerrados")
}
