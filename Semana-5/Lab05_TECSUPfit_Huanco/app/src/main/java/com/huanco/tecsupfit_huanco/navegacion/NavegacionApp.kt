package com.huanco.tecsupfit_huanco.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.huanco.tecsupfit_huanco.pantallas.PantallaConfirmacion
import com.huanco.tecsupfit_huanco.pantallas.PantallaDetalle
import com.huanco.tecsupfit_huanco.pantallas.PantallaInicio

@Composable
fun NavegacionApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Pantalla.Inicio.ruta
    ) {

        composable(Pantalla.Inicio.ruta) {
            PantallaInicio { claseId ->
                navController.navigate(Pantalla.Detalle.crearRuta(claseId))
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
                onVerReservas = { }
            )
        }
    }
}