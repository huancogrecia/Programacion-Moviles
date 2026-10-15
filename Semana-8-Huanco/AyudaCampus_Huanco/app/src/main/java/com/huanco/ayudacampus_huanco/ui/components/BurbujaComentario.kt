package com.huanco.ayudacampus_huanco.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BurbujaComentario(
    autorNombre: String,
    texto: String,
    fecha: String,
    esPropio: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if (esPropio) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.85f),
            shape = MaterialTheme.shapes.large,
            color = if (esPropio) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = if (esPropio) MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.onSurface
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(autorNombre, style = MaterialTheme.typography.labelMedium)
                Text(texto, style = MaterialTheme.typography.bodyMedium)
                Text(fecha, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
