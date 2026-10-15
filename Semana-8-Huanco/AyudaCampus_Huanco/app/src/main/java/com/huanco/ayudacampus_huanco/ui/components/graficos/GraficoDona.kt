package com.huanco.ayudacampus_huanco.ui.components.graficos

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.huanco.ayudacampus_huanco.model.DatoGrafico

@Composable
fun GraficoDona(datos: List<DatoGrafico>, modifier: Modifier = Modifier) {
    val total = datos.sumOf { it.valor.coerceAtLeast(0f).toDouble() }.toFloat()
    val descripcion = datos.joinToString { "${it.etiqueta}: ${it.valor.coerceAtLeast(0f).toInt()}" }
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (total > 0f) {
            Box(Modifier.size(180.dp), contentAlignment = Alignment.Center) {
                Canvas(Modifier.fillMaxSize().semantics {
                    contentDescription = "Tickets por estado. $descripcion"
                }) {
                    val grosor = 24.dp.toPx()
                    val diametro = size.minDimension - grosor
                    val origen = Offset((size.width - diametro) / 2f,
                        (size.height - diametro) / 2f)
                    var inicio = -90f
                    datos.forEach { dato ->
                        val angulo = dato.valor.coerceAtLeast(0f) / total * 360f
                        if (angulo > 0f) {
                            drawArc(color = dato.color, startAngle = inicio,
                                sweepAngle = angulo, useCenter = false,
                                topLeft = origen, size = Size(diametro, diametro),
                                style = Stroke(width = grosor))
                        }
                        inicio += angulo
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(total.toInt().toString(), style = MaterialTheme.typography.headlineMedium)
                    Text("Tickets", style = MaterialTheme.typography.labelMedium)
                }
            }
        } else {
            Text("Sin tickets para mostrar", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        datos.forEach { dato ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(Modifier.size(10.dp).background(dato.color, CircleShape))
                Text(dato.etiqueta, modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodySmall)
                Text(dato.valor.coerceAtLeast(0f).toInt().toString(),
                    style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}
