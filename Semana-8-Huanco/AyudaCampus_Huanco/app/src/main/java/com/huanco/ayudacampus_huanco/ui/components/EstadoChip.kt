package com.huanco.ayudacampus_huanco.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.ui.theme.EstadoAbierto
import com.huanco.ayudacampus_huanco.ui.theme.EstadoEnProceso
import com.huanco.ayudacampus_huanco.ui.theme.EstadoResuelto
import com.huanco.ayudacampus_huanco.ui.theme.EstadoCerrado

@Composable
fun EstadoChip(estado: EstadoTicket, modifier: Modifier = Modifier) {
    val texto = when (estado) {
        EstadoTicket.ABIERTO -> "Abierto"
        EstadoTicket.EN_PROCESO -> "En proceso"
        EstadoTicket.RESUELTO -> "Resuelto"
        EstadoTicket.CERRADO -> "Cerrado"
    }
    val color = when (estado) {
        EstadoTicket.ABIERTO -> EstadoAbierto
        EstadoTicket.EN_PROCESO -> EstadoEnProceso
        EstadoTicket.RESUELTO -> EstadoResuelto
        EstadoTicket.CERRADO -> EstadoCerrado
    }
    val oscuro = MaterialTheme.colorScheme.surface.luminance() < 0.5f
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = color.copy(alpha = 0.12f),
        contentColor = if (oscuro) MaterialTheme.colorScheme.onSurface else color,
        border = if (oscuro) BorderStroke(1.dp, color) else null
    ) {
        Text(
            text = texto,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}
