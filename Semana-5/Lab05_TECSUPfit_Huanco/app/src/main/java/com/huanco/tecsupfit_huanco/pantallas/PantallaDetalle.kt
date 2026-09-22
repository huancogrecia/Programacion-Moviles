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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Detalle de clase")

        Text(
            text = nombre,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = horario,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Descripción",
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Entrenamiento para mejorar tu condición física.",
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "8 de 12 cupos disponibles",
            modifier = Modifier.padding(top = 24.dp)
        )

        Button(
            onClick = onReservar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Reservar cupo")
        }
    }
}