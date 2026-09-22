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
fun PantallaRutinas() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
    ) {
        // Título estilizado
        Text(
            text = "Mis Rutinas",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta: Rutina de fuerza
        TarjetaRutinaItem(
            nombre = "Rutina de fuerza",
            duracion = "45 min",
            nivel = "Intermedio",
            emoji = "💪"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta: Rutina de cardio
        TarjetaRutinaItem(
            nombre = "Rutina de cardio",
            duracion = "30 min",
            nivel = "Básico",
            emoji = "🏃‍♂️"
        )
    }
}

@Composable
fun TarjetaRutinaItem(
    nombre: String,
    duracion: String,
    nivel: String,
    emoji: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo decorativo con emoji
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = emoji,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(12.dp)
                )
            }

            // Datos de la rutina
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⏳ $duracion",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = " • ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "📈 $nivel",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
