package com.huanco.tecsupfit_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun PantallaReservas(
    clasesReservadasIds: List<Int> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
    ) {
        // Título de la sección
        Text(
            text = "Mis Reservas",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Si la lista de IDs dinámicos contiene las clases, las mostramos dinámicamente.
        // O si está vacía (comportamiento por defecto inicial), mostramos las clases predefinidas de base.
        
        val mostrarYoga = clasesReservadasIds.contains(1) || clasesReservadasIds.isEmpty()
        val mostrarCross = clasesReservadasIds.contains(2) || clasesReservadasIds.isEmpty()
        val mostrarSpinning = clasesReservadasIds.contains(3)

        if (mostrarCross) {
            TarjetaReservaItem(
                titulo = "Cross Training",
                horarioInfo = "6:00 pm - Sala 1",
                estado = "Confirmada",
                emoji = "🏋️‍♂️",
                esActiva = true
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        if (mostrarYoga) {
            TarjetaReservaItem(
                titulo = "Yoga funcional",
                horarioInfo = "5:00 pm - Sala 2",
                estado = if (clasesReservadasIds.contains(1)) "Confirmada" else "Completada",
                emoji = "🧘",
                esActiva = clasesReservadasIds.contains(1)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        if (mostrarSpinning) {
            TarjetaReservaItem(
                titulo = "Spinning",
                horarioInfo = "7:00 pm - Sala 3",
                estado = "Confirmada",
                emoji = "🚴",
                esActiva = true
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        
        if (!mostrarYoga && !mostrarCross && !mostrarSpinning) {
            Text(
                text = "No tienes clases reservadas aún.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun TarjetaReservaItem(
    titulo: String,
    horarioInfo: String,
    estado: String,
    emoji: String,
    esActiva: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contenedor circular/redondeado para el avatar de la clase
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

            // Textos descriptivos
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "🕒 $horarioInfo",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Badge de estado personalizado
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (esActiva || estado == "Confirmada") MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = estado,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    color = if (esActiva || estado == "Confirmada") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
