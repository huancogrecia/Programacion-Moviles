package com.huanco.tecsupfit_huanco.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.huanco.tecsupfit_huanco.componentes.BarraNavegacion
import com.huanco.tecsupfit_huanco.pantallas.PantallaConfirmacion
import com.huanco.tecsupfit_huanco.pantallas.PantallaDetalle
import com.huanco.tecsupfit_huanco.pantallas.PantallaInicio
import com.huanco.tecsupfit_huanco.pantallas.PantallaPerfil
import com.huanco.tecsupfit_huanco.pantallas.PantallaReservas
import com.huanco.tecsupfit_huanco.pantallas.PantallaRutinas

@Composable
fun NavegacionApp() {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry.value?.destination?.route ?: Pantalla.Inicio.ruta

    // Creamos un estado reactivo para almacenar las clases reservadas por el usuario
    val listaReservas = remember { mutableStateListOf<Int>() }

    Scaffold(
        bottomBar = {
            BarraNavegacion(
                rutaActual = rutaActual,
                onNavegar = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Pantalla.Inicio.ruta,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Pantalla.Inicio.ruta) {
                PantallaInicio { claseId ->
                    navController.navigate(
                        Pantalla.Detalle.crearRuta(claseId)
                    )
                }
            }

            composable(
                route = Pantalla.Detalle.ruta,
                arguments = listOf(
                    navArgument("claseId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val claseId = backStackEntry.arguments?.getInt("claseId") ?: 1

                PantallaDetalle(
                    claseId = claseId,
                    onReservar = {
                        // Cuando el usuario confirma reservar, añadimos el ID a la lista reactiva
                        if (!listaReservas.contains(claseId)) {
                            listaReservas.add(claseId)
                        }
                        navController.navigate(
                            Pantalla.Confirmacion.crearRuta(claseId)
                        )
                    }
                )
            }

            composable(
                route = Pantalla.Confirmacion.ruta,
                arguments = listOf(
                    navArgument("claseId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val claseId = backStackEntry.arguments?.getInt("claseId") ?: 1

                PantallaConfirmacion(
                    claseId = claseId,
                    onVerReservas = {
                        navController.navigate(Pantalla.Reservas.ruta)
                    }
                )
            }

            composable(Pantalla.Reservas.ruta) {
                // Pasamos la lista de IDs reales que se han reservado
                PantallaReservas(clasesReservadasIds = listaReservas)
            }

            composable(Pantalla.Rutinas.ruta) {
                PantallaRutinas()
            }

            composable(Pantalla.Perfil.ruta) {
                PantallaPerfil()
            }
        }
    }
}
