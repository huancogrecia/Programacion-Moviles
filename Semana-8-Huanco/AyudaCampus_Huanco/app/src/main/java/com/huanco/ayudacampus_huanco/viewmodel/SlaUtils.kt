package com.huanco.ayudacampus_huanco.viewmodel

import com.huanco.ayudacampus_huanco.data.local.entity.Ticket
import com.huanco.ayudacampus_huanco.data.local.relation.TicketConDetalle
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class TicketUiItem(
    val detalle: TicketConDetalle,
    val slaTexto: String
)

fun relojMinutoFlow(): Flow<Long> = flow {
    while (true) {
        emit(System.currentTimeMillis())
        delay(60000L)
    }
}

fun calcularSlaTexto(ticket: Ticket, now: Long = System.currentTimeMillis()): String {
    val estado = try {
        EstadoTicket.valueOf(ticket.estado)
    } catch (_: Exception) {
        EstadoTicket.ABIERTO
    }
    if (estado == EstadoTicket.RESUELTO || estado == EstadoTicket.CERRADO) {
        return if (estado == EstadoTicket.RESUELTO) "Resuelto" else "Cerrado"
    }
    val prioridad = try {
        Prioridad.valueOf(ticket.prioridad)
    } catch (_: Exception) {
        Prioridad.MEDIA
    }
    val limiteMs = when (prioridad) {
        Prioridad.ALTA -> 4 * 3600 * 1000L
        Prioridad.MEDIA -> 24 * 3600 * 1000L
        Prioridad.BAJA -> 72 * 3600 * 1000L
    }
    val restanteMs = (ticket.fecha + limiteMs) - now
    if (restanteMs <= 0) {
        return "SLA vencido"
    }
    val totalMinutos = restanteMs / 60000
    val horas = totalMinutos / 60
    val minutos = totalMinutos % 60
    val dias = horas / 24
    return when {
        dias > 0 -> "${dias}d ${horas % 24}h"
        horas > 0 -> "${horas}h ${minutos}m"
        totalMinutos > 0 -> "${minutos}m"
        else -> "Menos de 1 min"
    }
}
