package com.huanco.lab05_clinicasalud_huanco.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfil(
    onRegresarClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mi perfil")
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
            Text(text = "Nombre: Grecia Huanco")

            Text(
                text = "Tipo de usuario: Paciente",
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Edad: 21 años",
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Correo: grecia.huanco@tecsup.edu.pe",
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}