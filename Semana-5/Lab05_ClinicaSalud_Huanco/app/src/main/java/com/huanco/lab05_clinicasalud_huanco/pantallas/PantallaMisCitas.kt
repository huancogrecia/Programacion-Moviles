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
fun PantallaMisCitas() {

    val citas = listOf(
        Triple("Dra. Ana Torres", "Martes 29 - 11:00 AM", "Confirmada"),
        Triple("Dr. Luis Vega", "Lunes 28 - 9:00 AM", "Completada"),
        Triple("Dra. Rosa Díaz", "Miércoles 30 - 3:00 PM", "Confirmada")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Mis citas",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(citas) { cita ->

                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "Médico: ${cita.first}")
                    Text(text = "Fecha y hora: ${cita.second}")
                    Text(text = "Estado: ${cita.third}")
                }
            }
        }
    }
}