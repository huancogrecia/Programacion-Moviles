package com.huanco.tecsupfit_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPerfil() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Mi perfil")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Grecia Huanco")
                Text("Estudiante TECSUP")
                Text("Diseño y Desarrollo de Software")
            }
        }

        Text(
            text = "Mis estadísticas",
            modifier = Modifier.padding(top = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Clases reservadas: 2")
                Text("Clases completadas: 1")
                Text("Rutinas disponibles: 2")
            }
        }
    }
}