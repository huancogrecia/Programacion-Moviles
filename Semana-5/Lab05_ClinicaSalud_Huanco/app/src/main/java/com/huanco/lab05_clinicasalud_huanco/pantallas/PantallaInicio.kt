package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(
    onMedicoClick: (String, String, String) -> Unit
) {

    val especialidades = listOf(
        "Cardiología",
        "Pediatría"
    )

    val medicos = listOf(
        Triple("Dra. Ana Torres", "Cardiología", "4.8"),
        Triple("Dr. Luis Vega", "Pediatría", "4.7"),
        Triple("Dra. Rosa Díaz", "Dermatología", "4.2")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Clínica Salud+")

        Text(
            text = "Hola, Grecia Huanco",
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

        Text(
            text = "Médicos disponibles",
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(medicos) { medico ->

                Button(
                    onClick = {
                        onMedicoClick(
                            medico.first,
                            medico.second,
                            medico.third
                        )
                    }
                ) {
                    Text(
                        text = "${medico.first} - ${medico.second} - ${medico.third}"
                    )
                }
            }
        }
    }
}