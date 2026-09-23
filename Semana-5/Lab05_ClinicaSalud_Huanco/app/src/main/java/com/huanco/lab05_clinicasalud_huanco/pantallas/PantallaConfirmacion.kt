package com.huanco.lab05_clinicasalud_huanco.pantallas

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
    medico: String,
    fecha: String,
    horario: String,
    onVolverInicio: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Confirmación de cita")

        Text(
            text = "Médico: $medico",
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Fecha: $fecha",
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = "Hora: $horario",
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = onVolverInicio,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(text = "Volver al inicio")
        }
    }
}