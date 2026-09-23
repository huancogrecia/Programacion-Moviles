package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPerfil() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Mi perfil")

        Text(
            text = "Nombre: Grecia Huanco",
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Tipo de usuario: Paciente",
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = "Edad: 22 años",
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = "Correo: grecia.huanco@tecsup.edu.pe",
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}