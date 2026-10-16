package com.huanco.ayudacampus_huanco.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.Settings
import com.huanco.ayudacampus_huanco.ui.soporte.PanelScreen
import com.huanco.ayudacampus_huanco.ui.soporte.BandejaScreen
import com.huanco.ayudacampus_huanco.viewmodel.PanelViewModel
import com.huanco.ayudacampus_huanco.viewmodel.BandejaViewModel
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.currentBackStackEntryAsState
import com.huanco.ayudacampus_huanco.AyudaApp
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
import com.huanco.ayudacampus_huanco.ui.cliente.DetalleTicketScreen
import com.huanco.ayudacampus_huanco.viewmodel.TicketDetalleViewModel
import com.huanco.ayudacampus_huanco.ui.cliente.NuevoTicketScreen
import com.huanco.ayudacampus_huanco.viewmodel.NuevoTicketViewModel
import com.huanco.ayudacampus_huanco.ui.cliente.MisTicketsScreen
import com.huanco.ayudacampus_huanco.viewmodel.AyudaViewModelFactory
import com.huanco.ayudacampus_huanco.viewmodel.MisTicketsViewModel
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
    val context = LocalContext.current
    val usuario by sesion.usuarioActual.collectAsStateWithLifecycle()
    val entrada by nav.currentBackStackEntryAsState()
    val ruta = entrada?.destination?.route
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
        Scaffold(
            bottomBar = {
                if (perfil == Rol.CLIENTE.name && ruta in listOf(Rutas.MIS_TICKETS, Rutas.NUEVO_TICKET, Rutas.PERFIL, Rutas.DETALLE_TICKET)) {
                    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                        val destinos = listOf(
                            Triple(Rutas.MIS_TICKETS, "Tickets", Icons.AutoMirrored.Outlined.List),
                            Triple(Rutas.NUEVO_TICKET, "Nuevo", Icons.Outlined.Add),
                            Triple(Rutas.PERFIL, "Perfil", Icons.Outlined.Person)
                        )
                        destinos.forEach { (destino, titulo, icono) ->
                            NavigationBarItem(
                                selected = ruta == destino || (destino == Rutas.MIS_TICKETS && ruta == Rutas.DETALLE_TICKET),
                                onClick = {
                                    nav.navigate(destino) {
                                        popUpTo(Rutas.MIS_TICKETS)
                                        launchSingleTop = true
                                    }
                                },
                                icon = { Icon(icono, contentDescription = null) },
                                label = { Text(titulo) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                } else if (perfil == Rol.ADMIN.name && ruta in listOf(Rutas.PANEL, Rutas.BANDEJA, Rutas.CATEGORIAS, Rutas.AJUSTES, Rutas.ATENDER_TICKET)) {
                    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                        val destinos = listOf(
                            Triple(Rutas.PANEL, "Panel", Icons.Outlined.BarChart),
                            Triple(Rutas.BANDEJA, "Tickets", Icons.AutoMirrored.Outlined.List),
                            Triple(Rutas.CATEGORIAS, "Categorías", Icons.AutoMirrored.Outlined.Label),
                            Triple(Rutas.AJUSTES, "Ajustes", Icons.Outlined.Settings)
                        )
                        destinos.forEach { (destino, titulo, icono) ->
                            NavigationBarItem(
                                selected = ruta == destino || (destino == Rutas.BANDEJA && ruta == Rutas.ATENDER_TICKET),
                                onClick = {
                                    nav.navigate(destino) {
                                        popUpTo(Rutas.PANEL)
                                        launchSingleTop = true
                                    }
                                },
                                icon = { Icon(icono, contentDescription = null) },
                                label = { Text(titulo) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                }
            }
        ) { contenidoPadding ->
            NavHost(navController = nav, startDestination = Rutas.SPLASH, modifier = Modifier.padding(contenidoPadding)) {
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
                    usuario?.let { actual ->
                        val factory = AyudaViewModelFactory((context.applicationContext as AyudaApp).repository, PreferenciasStore(context), actual)
                        val misTickets: MisTicketsViewModel = viewModel(key = "mis_tickets_${actual.id}", factory = factory)
                        MisTicketsScreen(misTickets, onDetalle = { nav.navigate("detalle_ticket/$it") }, onNuevo = { nav.navigate(Rutas.NUEVO_TICKET) })
                    }
                }
                composable(Rutas.NUEVO_TICKET) {
                    usuario?.let { actual ->
                        val factory = AyudaViewModelFactory((context.applicationContext as AyudaApp).repository, PreferenciasStore(context), actual)
                        val nuevoTicket: NuevoTicketViewModel = viewModel(key = "nuevo_ticket_${actual.id}", factory = factory)
                        NuevoTicketScreen(nuevoTicket, onVolver = { nav.popBackStack() }, onEnviado = {
                            nav.navigate(Rutas.MIS_TICKETS) {
                                popUpTo(Rutas.MIS_TICKETS) { inclusive = true }
                                launchSingleTop = true
                            }
                        })
                    }
                }
                composable(Rutas.PERFIL) {
                    Text("Perfil", Modifier.padding(20.dp), style = MaterialTheme.typography.headlineMedium)
                }
                composable(Rutas.DETALLE_TICKET, arguments = listOf(navArgument("id") { type = NavType.IntType })) { entradaDetalle ->
                    usuario?.let { actual ->
                        val id = requireNotNull(entradaDetalle.arguments).getInt("id")
                        val factory = AyudaViewModelFactory((context.applicationContext as AyudaApp).repository, PreferenciasStore(context), actual, id)
                        val detalle: TicketDetalleViewModel = viewModel(key = "detalle_${actual.id}_$id", factory = factory)
                        DetalleTicketScreen(detalle, onVolver = { nav.popBackStack() })
                    }
                }
                composable(Rutas.PANEL) {
                    val factory = AyudaViewModelFactory((context.applicationContext as AyudaApp).repository, PreferenciasStore(context))
                    val panel: PanelViewModel = viewModel(factory = factory)
                    PanelScreen(panel, onVerTickets = { nav.navigate(Rutas.BANDEJA) { launchSingleTop = true } })
                }
                composable(Rutas.BANDEJA) {
                    val factory = AyudaViewModelFactory((context.applicationContext as AyudaApp).repository, PreferenciasStore(context))
                    val bandeja: BandejaViewModel = viewModel(factory = factory)
                    BandejaScreen(bandeja, onAtender = { nav.navigate("atender_ticket/$it") })
                }
                composable(Rutas.ATENDER_TICKET, arguments = listOf(navArgument("id") { type = NavType.IntType })) { entradaAtender ->
                    Text("Atender ticket #${entradaAtender.arguments?.getInt("id")}", Modifier.padding(20.dp), style = MaterialTheme.typography.headlineMedium)
                }
                composable(Rutas.AJUSTES) {
                    Text("Ajustes", Modifier.padding(20.dp), style = MaterialTheme.typography.headlineMedium)
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
}
