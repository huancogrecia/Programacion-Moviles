package com.huanco.tecsupfit_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaConfirmacion(
    claseId: Int,
    onVerReservas: () -> Unit
) {

    val nombre = when (claseId) {
        1 -> "Yoga funcional"
        2 -> "Cross Training"
        else -> "Spinning"
    }

    val horario = when (claseId) {
        1 -> "5:00 pm - Sala 2"
        2 -> "6:00 pm - Sala 1"
        else -> "7:00 pm - Sala 3"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("¡Cupo reservado!")

        Text(
            text = nombre,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = horario,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = "Tu reserva fue registrada correctamente.",
            modifier = Modifier.padding(top = 24.dp)
        )

        Button(
            onClick = onVerReservas,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Ver mis reservas")
        }
    }
}