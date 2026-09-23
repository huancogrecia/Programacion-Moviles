package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaHistorialMedico() {

    val historial = listOf(
        Triple("Dr. Carlos Ruiz", "Cardiología", "10 de agosto"),
        Triple("Dra. María López", "Medicina general", "22 de julio"),
        Triple("Dr. José Pérez", "Pediatría", "15 de junio")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Historial médico",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(historial) { consulta ->

                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "Médico: ${consulta.first}")
                    Text(text = "Especialidad: ${consulta.second}")
                    Text(text = "Fecha: ${consulta.third}")
                }
            }
        }
    }
}