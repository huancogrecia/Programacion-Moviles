package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaAgendarCita(
    medico: String,
    onContinuarClick: (String, String, String) -> Unit
) {

    val fechas = listOf(
        "Lunes 28",
        "Martes 29",
        "Miércoles 30"
    )

    val horarios = listOf(
        "9:00 AM",
        "11:00 AM",
        "3:00 PM"
    )

    var fechaSeleccionada by remember {
        mutableStateOf("")
    }

    var horarioSeleccionado by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Agendar cita")

        Text(
            text = "Selecciona una fecha",
            modifier = Modifier.padding(top = 24.dp)
        )

        fechas.forEach { fecha ->

            Row {
                RadioButton(
                    selected = fechaSeleccionada == fecha,
                    onClick = {
                        fechaSeleccionada = fecha
                    }
                )

                Text(
                    text = fecha,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Text(
            text = "Selecciona un horario",
            modifier = Modifier.padding(top = 24.dp)
        )

        horarios.forEach { horario ->

            Row {
                RadioButton(
                    selected = horarioSeleccionado == horario,
                    onClick = {
                        horarioSeleccionado = horario
                    }
                )

                Text(
                    text = horario,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Button(
            onClick = {
                if (fechaSeleccionada.isNotEmpty() &&
                    horarioSeleccionado.isNotEmpty()
                ) {
                    onContinuarClick(
                        medico,
                        fechaSeleccionada,
                        horarioSeleccionado
                    )
                }
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(text = "Continuar")
        }
    }
}