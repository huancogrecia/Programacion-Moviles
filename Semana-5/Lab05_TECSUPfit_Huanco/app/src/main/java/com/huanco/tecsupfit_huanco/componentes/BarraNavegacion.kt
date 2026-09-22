package com.huanco.tecsupfit_huanco.componentes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {

        opciones.forEach { opcion ->
            val esSeleccionado = rutaActual == opcion

            val emojiIcon = when (opcion) {
                "inicio" -> "🏠"
                "reservas" -> "📅"
                "rutinas" -> "💪"
                else -> "👤"
            }

            val etiqueta = when (opcion) {
                "inicio" -> "Inicio"
                "reservas" -> "Reservas"
                "rutinas" -> "Rutinas"
                else -> "Perfil"
            }

            NavigationBarItem(
                selected = esSeleccionado,
                onClick = {
                    if (!esSeleccionado) {
                        onNavegar(opcion)
                    }
                },
                icon = {
                    Text(
                        text = emojiIcon,
                        fontSize = 20.sp
                    )
                },
                label = {
                    Text(
                        text = etiqueta,
                        fontWeight = if (esSeleccionado) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 12.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
