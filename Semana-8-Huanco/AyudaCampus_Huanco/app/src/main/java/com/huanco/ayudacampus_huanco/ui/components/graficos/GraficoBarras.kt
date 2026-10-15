package com.huanco.ayudacampus_huanco.ui.components.graficos

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.huanco.ayudacampus_huanco.model.DatoGrafico

@Composable
fun GraficoBarras(datos: List<DatoGrafico>, modifier: Modifier = Modifier) {
    val maximo = datos.maxOfOrNull { it.valor.coerceAtLeast(0f) } ?: 0f
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (maximo <= 0f) {
            Text("Sin tickets para mostrar", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (datos.isNotEmpty()) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                datos.forEach { dato ->
                    val valor = dato.valor.coerceAtLeast(0f)
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(valor.toInt().toString(), style = MaterialTheme.typography.labelMedium)
                        Canvas(Modifier.fillMaxWidth().height(140.dp).semantics {
                            contentDescription = "${dato.etiqueta}: ${valor.toInt()} tickets"
                        }) {
                            val altura = if (maximo > 0f) valor / maximo * size.height else 0f
                            if (altura > 0f) {
                                drawRoundRect(color = dato.color,
                                    topLeft = Offset(size.width * 0.15f, size.height - altura),
                                    size = Size(size.width * 0.7f, altura),
                                    cornerRadius = CornerRadius(4.dp.toPx()))
                            }
                        }
                        Text(dato.etiqueta, style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
