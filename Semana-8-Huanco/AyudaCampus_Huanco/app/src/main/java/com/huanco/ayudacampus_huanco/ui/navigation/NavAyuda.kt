package com.huanco.ayudacampus_huanco.ui.navigation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.huanco.ayudacampus_huanco.ui.cliente.SplashScreen
import com.huanco.ayudacampus_huanco.ui.components.AppTopBar
import com.huanco.ayudacampus_huanco.ui.soporte.CategoriasScreen
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel
@Composable
fun NavAyuda(vm: CategoriasViewModel) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Rutas.SPLASH) {
        composable(Rutas.SPLASH) {
            SplashScreen { nav.navigate(Rutas.CATEGORIAS) { popUpTo(Rutas.SPLASH) { inclusive = true } } }
        }
        composable(Rutas.CATEGORIAS) {
            Scaffold(topBar = { AppTopBar("Categorías") }) { padding ->
                Column(Modifier.fillMaxSize().padding(padding)) {
                    Text("Administra las categorías de atención", Modifier.padding(horizontal = 20.dp, vertical = 12.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                    CategoriasScreen(vm, onEditar = {  })
                }
            }
        }
    }
}
