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
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadAlta
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadMedia
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadBaja

@Composable
fun PrioridadChip(prioridad: Prioridad, modifier: Modifier = Modifier) {
    val texto = when (prioridad) {
        Prioridad.ALTA -> "Alta"
        Prioridad.MEDIA -> "Media"
        Prioridad.BAJA -> "Baja"
    }
    val color = when (prioridad) {
        Prioridad.ALTA -> PrioridadAlta
        Prioridad.MEDIA -> PrioridadMedia
        Prioridad.BAJA -> PrioridadBaja
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
