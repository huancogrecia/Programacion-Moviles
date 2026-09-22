package com.huanco.tecsupfit_huanco.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(
    onClaseClick: (Int) -> Unit
) {

    var filtroSeleccionado by remember {
        mutableStateOf("Hoy")
    }

    val filtros = listOf("Hoy", "Esta semana")

    val clases = listOf(
        1 to "Yoga funcional - 5:00 pm - Sala 2 - 45 min",
        2 to "Cross Training - 6:00 pm - Sala 1 - 45 min",
        3 to "Spinning - 7:00 pm - Sala 3 - 50 min"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {

        Text(
            text = "TECSUP Fit",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Hola, Grecia Huanco",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 24.dp
            )
        )

        Text(
            text = "¿Qué clase quieres tomar?",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 4.dp
            )
        )

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filtros) { filtro ->

                Button(
                    onClick = {
                        filtroSeleccionado = filtro
                    }
                ) {
                    Text(filtro)
                }
            }
        }


        Text(
            text = "Filtro seleccionado: $filtroSeleccionado",
            modifier = Modifier.padding(horizontal = 16.dp)
        )


        Text(
            text = "Clases disponibles",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )


        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(clases) { clase ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClaseClick(clase.first)
                        }
                ) {
                    Text(
                        text = clase.second,
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }
        }
    }
}