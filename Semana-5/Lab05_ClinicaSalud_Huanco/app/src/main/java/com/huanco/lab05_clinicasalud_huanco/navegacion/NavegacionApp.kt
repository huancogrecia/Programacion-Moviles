package com.huanco.lab05_clinicasalud_huanco.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaAgendarCita
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaConfirmacion
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaHistorialMedico
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaInicio
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaMisCitas
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaPerfilMedico
import com.huanco.lab05_clinicasalud_huanco.pantallas.PantallaPerfil

@Composable
fun NavegacionApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {

            PantallaInicio(
                onMedicoClick = { nombre, especialidad, calificacion ->

                    navController.navigate(
                        "perfil/$nombre/$especialidad/$calificacion"
                    )
                },
                onMisCitasClick = {
                    navController.navigate("misCitas")
                },
                onHistorialClick = {
                    navController.navigate("historial")
                },
                onPerfilClick = {
                    navController.navigate("perfilUsuario")
                }
            )
        }

        composable(
            "perfil/{nombre}/{especialidad}/{calificacion}"
        ) { backStackEntry ->

            val nombre =
                backStackEntry.arguments?.getString("nombre") ?: ""

            val especialidad =
                backStackEntry.arguments?.getString("especialidad") ?: ""

            val calificacion =
                backStackEntry.arguments?.getString("calificacion") ?: ""

            PantallaPerfilMedico(
                nombre = nombre,
                especialidad = especialidad,
                calificacion = calificacion,
                onAgendarClick = {
                    navController.navigate("agendar/$nombre")
                }
            )
        }

        composable(
            "agendar/{medico}"
        ) { backStackEntry ->

            val medico =
                backStackEntry.arguments?.getString("medico") ?: ""

            PantallaAgendarCita(
                medico = medico,
                onContinuarClick = { nombre, fecha, horario ->

                    navController.navigate(
                        "confirmacion/$nombre/$fecha/$horario"
                    )
                }
            )
        }

        composable(
            "confirmacion/{medico}/{fecha}/{horario}"
        ) { backStackEntry ->

            val medico =
                backStackEntry.arguments?.getString("medico") ?: ""

            val fecha =
                backStackEntry.arguments?.getString("fecha") ?: ""

            val horario =
                backStackEntry.arguments?.getString("horario") ?: ""

            PantallaConfirmacion(
                medico = medico,
                fecha = fecha,
                horario = horario,
                onVolverInicio = {
                    navController.navigate("inicio")
                }
            )
        }
        composable("misCitas") {
            PantallaMisCitas(
                onRegresarClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("historial") {
            PantallaHistorialMedico(
                onRegresarClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("perfilUsuario") {
            PantallaPerfil(
                onRegresarClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}