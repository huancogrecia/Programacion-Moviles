package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio() {

    val especialidades = listOf(
        "Cardiología",
        "Pediatría"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Clínica Salud+")

        Text(
            text = "Hola, Juan",
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        Text(text = "Especialidades")

        LazyRow(
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(especialidades) { especialidad ->

                Button(
                    onClick = { }
                ) {
                    Text(text = especialidad)
                }
            }
        }
    }
}