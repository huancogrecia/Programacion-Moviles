package com.huanco.tecsupfit_huanco.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PantallaDetalle(
    claseId: Int,
    onReservar: () -> Unit
) {

    val nombre = when (claseId) {
        1 -> "Yoga funcional"
        2 -> "Cross Training"
        else -> "Spinning"
    }

    val horario = when (claseId) {
        1 -> "5:00 pm - Sala 2 - 45 min"
        2 -> "6:00 pm - Sala 1 - 45 min"
        else -> "7:00 pm - Sala 3 - 50 min"
    }

    // Dividimos los datos de horario para organizarlos de manera limpia y moderna
    val partes = horario.split(" - ")
    val hora = partes.getOrNull(0) ?: horario
    val sala = partes.getOrNull(1) ?: ""
    val duracion = partes.getOrNull(2) ?: ""

    // Emoji o ícono representativo según la clase seleccionada
    val emoji = when (claseId) {
        1 -> "🧘"
        2 -> "🏋️‍♂️"
        else -> "🚴"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
    ) {
        // Título del encabezado superior de la vista
        Text(
            text = "Detalle de Clase",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bloque de Identificación de Clase (Emoji gigante + Nombre)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(64.dp)
            ) {
                BoxDetailEmoji(emoji = emoji)
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "TECSUP Fit Premium",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección Estructurada de Horarios, Lugar y Tiempos
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ItemDetalleLinear(icono = "🕒", propiedad = "Hora de inicio", valor = hora)
                Spacer(modifier = Modifier.height(12.dp))
                ItemDetalleLinear(icono = "📍", propiedad = "Ubicación / Sala", valor = sala)
                Spacer(modifier = Modifier.height(12.dp))
                ItemDetalleLinear(icono = "⏳", propiedad = "Duración total", valor = duracion)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bloque de Descripción de la Actividad
        Text(
            text = "Descripción de la actividad",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Entrenamiento de alto rendimiento diseñado para mejorar tu condición física, resistencia cardiovascular y fuerza muscular mediante rutinas guiadas por profesionales calificados.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Banner indicador de disponibilidad de aforo
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🔥",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "¡Apresúrate! Quedan 8 de 12 cupos disponibles",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón principal de Reservas Deportivo
        Button(
            onClick = onReservar,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "Confirmar Reserva",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun BoxDetailEmoji(emoji: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = emoji, style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ItemDetalleLinear(icono: String, propiedad: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = icono, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(end = 8.dp))
            Text(text = propiedad, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(text = valor, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
    }
}