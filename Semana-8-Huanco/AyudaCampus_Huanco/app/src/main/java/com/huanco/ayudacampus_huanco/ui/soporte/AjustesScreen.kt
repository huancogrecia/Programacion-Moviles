package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.ui.cliente.FilaPreferencia
import com.huanco.ayudacampus_huanco.ui.cliente.TarjetaPerfil
import com.huanco.ayudacampus_huanco.ui.components.AyudaButton
import com.huanco.ayudacampus_huanco.viewmodel.SesionViewModel

@Composable
fun AjustesScreen(vm: SesionViewModel) {
    val usuario by vm.usuarioActual.collectAsStateWithLifecycle()
    val oscuro by vm.modoOscuro.collectAsStateWithLifecycle()
    val notificaciones by vm.notificaciones.collectAsStateWithLifecycle()
    val recibirTickets by vm.recibirTickets.collectAsStateWithLifecycle()
    val procesando by vm.ingresando.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text("Ajustes", style = MaterialTheme.typography.headlineLarge)
        TarjetaPerfil(usuario?.nombre.orEmpty(), usuario?.correo.orEmpty(), "Soporte TI")
        AyudaButton("Cambiar de perfil", vm::cambiarPerfil, habilitado = !procesando)
        Text("Modo práctica: ir a Estudiante", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("Preferencias", style = MaterialTheme.typography.titleLarge)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
            Column(Modifier.padding(16.dp)) {
                FilaPreferencia("Modo oscuro", oscuro, vm::guardarModoOscuro)
                HorizontalDivider()
                FilaPreferencia("Notificaciones", notificaciones, vm::guardarNotificaciones)
                HorizontalDivider()
                FilaPreferencia("Recibir tickets", recibirTickets, vm::guardarRecibirTickets)
            }
        }
        if (error != null) Text(error.orEmpty(), color = MaterialTheme.colorScheme.error)
        OutlinedButton(onClick = vm::cerrarSesion, enabled = !procesando, modifier = Modifier.fillMaxWidth()) { Text("Cerrar sesión", color = MaterialTheme.colorScheme.error) }
    }
}
