package com.huanco.tecsupfit_huanco.navegacion

sealed class Pantalla(val ruta: String) {

    object Inicio : Pantalla("inicio")

    object Detalle : Pantalla("detalle/{claseId}") {
        fun crearRuta(claseId: Int): String {
            return "detalle/$claseId"
        }
    }

    object Confirmacion : Pantalla("confirmacion/{claseId}") {
        fun crearRuta(claseId: Int): String {
            return "confirmacion/$claseId"
        }
    }

    object Reservas : Pantalla("reservas")

    object Rutinas : Pantalla("rutinas")

    object Perfil : Pantalla("perfil")
}