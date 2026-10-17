package com.huanco.ayudacampus_huanco.ui.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.ui.components.AyudaButton
import com.huanco.ayudacampus_huanco.ui.theme.EstadoAbierto
import com.huanco.ayudacampus_huanco.ui.theme.EstadoResuelto
import com.huanco.ayudacampus_huanco.viewmodel.SesionViewModel

@Composable
fun PerfilScreen(vm: SesionViewModel) {
    val usuario by vm.usuarioActual.collectAsStateWithLifecycle()
    val tickets by vm.ticketsUsuario.collectAsStateWithLifecycle()
    val oscuro by vm.modoOscuro.collectAsStateWithLifecycle()
    val notificaciones by vm.notificaciones.collectAsStateWithLifecycle()
    val procesando by vm.ingresando.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text("Perfil", style = MaterialTheme.typography.headlineLarge)
        TarjetaPerfil(usuario?.nombre.orEmpty(), usuario?.correo.orEmpty(), "Estudiante")
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ContadorPerfil(tickets.count { it.ticket.estado == EstadoTicket.ABIERTO.name }, "Abiertos", EstadoAbierto, Modifier.weight(1f))
            ContadorPerfil(tickets.count { it.ticket.estado == EstadoTicket.RESUELTO.name }, "Resueltos", EstadoResuelto, Modifier.weight(1f))
            ContadorPerfil(tickets.size, "Total", MaterialTheme.colorScheme.primary, Modifier.weight(1f))
        }
        AyudaButton("Cambiar de perfil", vm::cambiarPerfil, habilitado = !procesando)
        Text("Modo práctica: ir a Soporte TI", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text("Preferencias", style = MaterialTheme.typography.titleLarge)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
            Column(Modifier.padding(16.dp)) {
                FilaPreferencia("Modo oscuro", oscuro, vm::guardarModoOscuro)
                HorizontalDivider()
                FilaPreferencia("Notificaciones", notificaciones, vm::guardarNotificaciones)
            }
        }
        if (error != null) Text(error.orEmpty(), color = MaterialTheme.colorScheme.error)
        OutlinedButton(onClick = vm::cerrarSesion, enabled = !procesando, modifier = Modifier.fillMaxWidth()) { Text("Cerrar sesión", color = MaterialTheme.colorScheme.error) }
    }
}

@Composable
fun TarjetaPerfil(nombre: String, correo: String, rol: String) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.large) {
                Icon(Icons.Outlined.Person, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(14.dp))
            }
            Column {
                Text(nombre, style = MaterialTheme.typography.titleMedium)
                Text(correo, style = MaterialTheme.typography.bodyMedium)
                Text(rol, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun FilaPreferencia(titulo: String, valor: Boolean, onCambiar: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(titulo, Modifier.weight(1f), style = MaterialTheme.typography.titleMedium)
        Switch(checked = valor, onCheckedChange = onCambiar, modifier = Modifier.semantics { contentDescription = titulo })
    }
}

@Composable
private fun ContadorPerfil(cantidad: Int, titulo: String, color: Color, modifier: Modifier) {
    Surface(modifier = modifier, shape = MaterialTheme.shapes.large, color = color.copy(alpha = 0.12f)) {
        Column(Modifier.padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(cantidad.toString(), style = MaterialTheme.typography.headlineMedium)
            Text(titulo, style = MaterialTheme.typography.labelLarge)
        }
    }
}
