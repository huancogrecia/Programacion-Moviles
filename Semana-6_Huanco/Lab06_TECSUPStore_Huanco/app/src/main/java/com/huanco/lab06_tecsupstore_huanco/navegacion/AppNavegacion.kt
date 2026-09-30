package com.huanco.lab06_tecsupstore_huanco.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.huanco.lab06_tecsupstore_huanco.componentes.AppDrawer
import com.huanco.lab06_tecsupstore_huanco.screens.FavoritesScreen
import com.huanco.lab06_tecsupstore_huanco.screens.HomeScreen
import com.huanco.lab06_tecsupstore_huanco.screens.PedidosScreen
import com.huanco.lab06_tecsupstore_huanco.screens.ProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavegacion() {

    val navController = rememberNavController()

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val rutaActual =
        backStackEntry?.destination?.route ?: Rutas.INICIO

    fun navegar(ruta: String) {
        navController.navigate(ruta) {
            launchSingleTop = true
        }

        scope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                rutaActual = rutaActual,
                onInicioClick = {
                    navegar(Rutas.INICIO)
                },
                onPedidosClick = {
                    navegar(Rutas.PEDIDOS)
                },
                onFavoritosClick = {
                    navegar(Rutas.FAVORITOS)
                },
                onPerfilClick = {
                    navegar(Rutas.PERFIL)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TECSUP Store")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = Rutas.INICIO
            ) {
                composable(Rutas.INICIO) {
                    HomeScreen(
                        paddingValues = paddingValues
                    )
                }

                composable(Rutas.PEDIDOS) {
                    PedidosScreen(
                        paddingValues = paddingValues
                    )
                }

                composable(Rutas.FAVORITOS) {
                    FavoritesScreen(
                        paddingValues = paddingValues
                    )
                }

                composable(Rutas.PERFIL) {
                    ProfileScreen(
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}