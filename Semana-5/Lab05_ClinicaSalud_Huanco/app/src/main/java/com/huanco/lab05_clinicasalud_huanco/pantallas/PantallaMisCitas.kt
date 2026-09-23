package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMisCitas(
    onRegresarClick: () -> Unit
) {
    val citas = listOf(
        Triple("Dra. Ana Torres", "Martes 29 - 11:00 AM", "Confirmada"),
        Triple("Dr. Luis Vega", "Lunes 28 - 9:00 AM", "Completada"),
        Triple("Dra. Rosa Díaz", "Miércoles 30 - 3:00 PM", "Confirmada")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mis citas")
                },
                navigationIcon = {
                    TextButton(
                        onClick = {
                            onRegresarClick()
                        }
                    ) {
                        Text(text = "←")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(citas) { cita ->
                    Column(
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text(text = "Médico: ${cita.first}")
                        Text(text = "Fecha y hora: ${cita.second}")
                        Text(
                            text = "Estado: ${cita.third}",
                            color = if (cita.third == "Confirmada") {
                                Color(0xFF2E7D32)
                            } else {
                                Color(0xFF1565C0)
                            }
                        )
                    }
                }
            }
        }
    }
}