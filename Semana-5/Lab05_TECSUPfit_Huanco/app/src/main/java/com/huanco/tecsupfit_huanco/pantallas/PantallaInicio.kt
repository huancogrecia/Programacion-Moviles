package com.huanco.tecsupfit_huanco.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(
    onClaseClick: (Int) -> Unit
) {

    var filtroSeleccionado by remember {
        mutableStateOf("Hoy")
    }

    val filtros = listOf("Hoy", "Esta semana")

    val clases = listOf(
        1 to "Yoga funcional - 5:00 pm - Sala 2 - 45 min",
        2 to "Cross Training - 6:00 pm - Sala 1 - 45 min",
        3 to "Spinning - 7:00 pm - Sala 3 - 50 min"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        // Encabezado principal deportivo y moderno
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "TECSUP Fit",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = "¡Supera tus límites hoy!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "💪",
                style = MaterialTheme.typography.headlineLarge
            )
        }

        // Tarjeta de bienvenida elegante
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Hola, Grecia Huanco 👋",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "¿Qué clase quieres tomar?",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                )
            }
        }

        // Sección de Filtros con estado visual activo/inactivo
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filtros) { filtro ->
                val esSeleccionado = filtroSeleccionado == filtro
                Button(
                    onClick = {
                        filtroSeleccionado = filtro
                    },
                    colors = if (esSeleccionado) {
                        ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    } else {
                        ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    }
                ) {
                    Text(
                        text = filtro,
                        fontWeight = if (esSeleccionado) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Text(
            text = "Filtro seleccionado: $filtroSeleccionado",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Clases disponibles",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        // Lista de clases optimizada con diseño estructurado
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(clases) { clase ->
                val partes = clase.second.split(" - ")
                val nombreClase = partes.getOrNull(0) ?: clase.second
                val hora = partes.getOrNull(1) ?: ""
                val sala = partes.getOrNull(2) ?: ""
                val duracion = partes.getOrNull(3) ?: ""

                // Emoji temático según el tipo de clase
                val emoji = when {
                    nombreClase.contains("Yoga", ignoreCase = true) -> "🧘"
                    nombreClase.contains("Cross", ignoreCase = true) -> "🏋️‍♂️"
                    nombreClase.contains("Spinning", ignoreCase = true) -> "🚴"
                    else -> "⚡"
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClaseClick(clase.first)
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Contenedor del ícono/emoji de la clase
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            Text(
                                text = emoji,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(10.dp)
                            )
                        }

                        // Detalles de la clase en formato estructurado
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = nombreClase,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (hora.isNotEmpty()) EtiquetaClase(texto = "🕒 $hora")
                                if (sala.isNotEmpty()) EtiquetaClase(texto = "📍 $sala")
                                if (duracion.isNotEmpty()) EtiquetaClase(texto = "⏳ $duracion")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EtiquetaClase(texto: String) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Text(
            text = texto,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}