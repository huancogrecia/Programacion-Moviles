package com.huanco.tecsupfit_huanco.componentes

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BarraNavegacion(
    rutaActual: String,
    onNavegar: (String) -> Unit
) {

    val opciones = listOf(
        "inicio",
        "reservas",
        "rutinas",
        "perfil"
    )

    NavigationBar {

        opciones.forEach { opcion ->

            NavigationBarItem(
                selected = rutaActual == opcion,
                onClick = {
                    onNavegar(opcion)
                },
                icon = { },
                label = {
                    Text(
                        when (opcion) {
                            "inicio" -> "Inicio"
                            "reservas" -> "Reservas"
                            "rutinas" -> "Rutinas"
                            else -> "Perfil"
                        }
                    )
                }
            )
        }
    }
}