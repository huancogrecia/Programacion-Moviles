package com.huanco.ayudacampus_huanco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.huanco.ayudacampus_huanco.model.EstadoTicket

@Composable
fun StepperTicket(estado: EstadoTicket, modifier: Modifier = Modifier) {
    val estados = listOf(EstadoTicket.ABIERTO, EstadoTicket.EN_PROCESO,
        EstadoTicket.RESUELTO, EstadoTicket.CERRADO)
    val nombres = listOf("Abierto", "En proceso", "Resuelto", "Cerrado")
    val actual = estados.indexOf(estado)
    Row(modifier = modifier.fillMaxWidth().semantics(mergeDescendants = true) {
        contentDescription = "Estado del ticket: ${nombres[actual]}"
    }) {
        estados.forEachIndexed { indice, _ ->
            val alcanzado = indice <= actual
            Column(modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.fillMaxWidth().height(32.dp),
                    contentAlignment = Alignment.Center) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.weight(1f).height(2.dp).then(
                            if (indice > 0) Modifier.background(
                                if (indice <= actual) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outlineVariant
                            ) else Modifier
                        ))
                        Spacer(Modifier.width(32.dp))
                        Box(Modifier.weight(1f).height(2.dp).then(
                            if (indice < estados.lastIndex) Modifier.background(
                                if (indice < actual) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outlineVariant
                            ) else Modifier
                        ))
                    }
                    Surface(shape = CircleShape, modifier = Modifier.size(32.dp),
                        color = if (alcanzado) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceContainerHighest,
                        contentColor = if (alcanzado) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurfaceVariant) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("${indice + 1}", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text(nombres[indice], style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = if (alcanzado) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
