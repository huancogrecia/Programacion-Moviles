package com.huanco.lab05_navegacion_huanco.navigation


sealed class Screen(val route: String) {

    // Pantalla de inicio
    object Home : Screen("home")


    // Pantalla que muestra la lista de elementos
    object List : Screen("list")


    // Pantalla del perfil del usuario
    object Profile : Screen("profile")


    // Ruta con argumento
    object Detail : Screen("detail/{itemId}") {


        // Construye la ruta final con el ID seleccionado
        fun createRoute(itemId: Int): String = "detail/$itemId"
    }
}