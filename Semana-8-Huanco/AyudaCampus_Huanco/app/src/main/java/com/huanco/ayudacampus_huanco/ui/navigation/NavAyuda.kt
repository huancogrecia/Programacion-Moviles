package com.huanco.ayudacampus_huanco.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huanco.ayudacampus_huanco.model.Rol
import com.huanco.ayudacampus_huanco.ui.cliente.LoginScreen
import com.huanco.ayudacampus_huanco.ui.cliente.SplashScreen
import com.huanco.ayudacampus_huanco.ui.components.AppTopBar
import com.huanco.ayudacampus_huanco.ui.soporte.CategoriasScreen
import com.huanco.ayudacampus_huanco.ui.theme.AyudaTheme
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel
import com.huanco.ayudacampus_huanco.viewmodel.SesionViewModel

@Composable
fun NavAyuda(vm: CategoriasViewModel, sesion: SesionViewModel) {
    val nav = rememberNavController()
    val perfil by sesion.perfilActivo.collectAsStateWithLifecycle()
    val usuarioId by sesion.usuarioId.collectAsStateWithLifecycle()
    val cargada by sesion.sesionCargada.collectAsStateWithLifecycle()
    val modoOscuro by sesion.modoOscuro.collectAsStateWithLifecycle()
    var rolSeleccionado by rememberSaveable { mutableStateOf(Rol.CLIENTE.name) }
    var splashTerminado by rememberSaveable { mutableStateOf(false) }
    val rol = if (perfil == Rol.ADMIN.name || (perfil.isEmpty() && rolSeleccionado == Rol.ADMIN.name)) Rol.ADMIN else Rol.CLIENTE
    AyudaTheme(rol, modoOscuro) {
        LaunchedEffect(splashTerminado, cargada, perfil, usuarioId) {
            if (splashTerminado && cargada) {
                val destino = when {
                    usuarioId > 0 && perfil == Rol.CLIENTE.name -> Rutas.MIS_TICKETS
                    usuarioId > 0 && perfil == Rol.ADMIN.name -> Rutas.PANEL
                    else -> Rutas.LOGIN
                }
                nav.navigate(destino) {
                    popUpTo(nav.graph.id) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
        NavHost(navController = nav, startDestination = Rutas.SPLASH) {
            composable(Rutas.SPLASH) {
                SplashScreen { splashTerminado = true }
            }
            composable(Rutas.LOGIN) {
                Scaffold { padding ->
                    Column(Modifier.fillMaxSize().padding(padding)) {
                        LoginScreen(sesion, Rol.valueOf(rolSeleccionado)) { rolSeleccionado = it.name }
                    }
                }
            }
            composable(Rutas.MIS_TICKETS) {
                Scaffold(topBar = { AppTopBar("Mis tickets") }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding)) { }
                }
            }
            composable(Rutas.PANEL) {
                Scaffold(topBar = { AppTopBar("Panel") }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding)) { }
                }
            }
            composable(Rutas.CATEGORIAS) {
                Scaffold(topBar = { AppTopBar("Categorías") }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding)) {
                        Text("Administra las categorías de atención", Modifier.padding(horizontal = 20.dp, vertical = 12.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                        CategoriasScreen(vm, onEditar = { })
                    }
                }
            }
        }
    }
}
