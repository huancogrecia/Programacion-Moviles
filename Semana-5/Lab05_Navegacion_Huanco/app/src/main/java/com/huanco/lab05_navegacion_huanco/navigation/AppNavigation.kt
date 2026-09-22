package com.huanco.lab05_navegacion_huanco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.huanco.lab05_navegacion_huanco.screens.DetailScreen
import com.huanco.lab05_navegacion_huanco.screens.HomeScreen
import com.huanco.lab05_navegacion_huanco.screens.ListScreen
import com.huanco.lab05_navegacion_huanco.screens.ProfileScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.List.route) {
            ListScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->

            val itemId =
                backStackEntry.arguments?.getInt("itemId") ?: 0

            DetailScreen(navController, itemId)
        }
    }
}
