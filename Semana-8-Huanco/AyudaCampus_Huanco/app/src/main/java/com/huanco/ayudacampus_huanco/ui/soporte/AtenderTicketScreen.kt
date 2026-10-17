package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.components.*
import com.huanco.ayudacampus_huanco.viewmodel.AtenderViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AtenderTicketScreen(vm: AtenderViewModel, onVolver: () -> Unit) {
    val detalle by vm.ticket.collectAsStateWithLifecycle()
    val cargando by vm.cargando.collectAsStateWithLifecycle()
    val estado by vm.estado.collectAsStateWithLifecycle()
    val prioridad by vm.prioridad.collectAsStateWithLifecycle()
    val respuesta by vm.respuesta.collectAsStateWithLifecycle()
    val procesando by vm.procesando.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    val mensaje by vm.mensaje.collectAsStateWithLifecycle()
    val actual = detalle
    LazyColumn(
        modifier = Modifier.fillMaxSize().imePadding(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolver, enabled = !procesando) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, "Volver a la bandeja")
                }
                Text(actual?.let { "Atender ticket #${it.ticket.id}" } ?: "Atender ticket", style = MaterialTheme.typography.headlineSmall)
            }
        }
        if (actual == null) {
            item { Text(if (cargando) "Cargando ticket…" else "Ticket no disponible") }
        } else {
            val cerrado = actual.ticket.estado == EstadoTicket.CERRADO.name
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
                    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(actual.ticket.titulo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(actual.cliente.nombre)
                        Text(actual.cliente.correo, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("${actual.categoria.nombre} · ${actual.ticket.ubicacion}")
                        Text(actual.ticket.descripcion)
                    }
                }
            }
            item {
                Text("Estado", style = MaterialTheme.typography.titleMedium)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    EstadoTicket.entries.forEach { opcion ->
                        val titulo = when (opcion) {
                            EstadoTicket.ABIERTO -> "Abierto"
                            EstadoTicket.EN_PROCESO -> "En proceso"
                            EstadoTicket.RESUELTO -> "Resuelto"
                            EstadoTicket.CERRADO -> "Cerrado"
                        }
                        FilterChip(selected = estado == opcion, onClick = { vm.cambiarEstado(opcion) },
                            label = { Text(titulo) }, enabled = !cerrado && !procesando && opcion != EstadoTicket.CERRADO)
                    }
                }
            }
            item {
                Text("Prioridad", style = MaterialTheme.typography.titleMedium)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Prioridad.entries.forEach { opcion ->
                        FilterChip(selected = prioridad == opcion, onClick = { vm.cambiarPrioridad(opcion) },
                            label = { Text(opcion.name.lowercase().replaceFirstChar { it.uppercase() }) }, enabled = !cerrado && !procesando)
                    }
                }
            }
            if (cerrado) {
                item { Text("Este ticket está cerrado", color = MaterialTheme.colorScheme.onSurfaceVariant) }
            }
            item { Text("Conversación", style = MaterialTheme.typography.titleLarge) }
            if (actual.comentarios.isEmpty()) {
                item { Text("Aún no hay comentarios") }
            }
            items(actual.comentarios.sortedWith(compareBy({ it.fecha }, { it.id })), key = { "comentario_${it.id}" }) { comentario ->
                BurbujaComentario(comentario.autorNombre, comentario.texto,
                    SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()).format(Date(comentario.fecha)),
                    esPropio = comentario.autorId == vm.usuario.id)
            }
            if (!cerrado) {
                item { AyudaTextField(respuesta, vm::cambiarRespuesta, "Responder al cliente", habilitado = !procesando, unaLinea = false) }
                item { AyudaButton("Enviar respuesta", { vm.responder(respuesta) }, habilitado = respuesta.isNotBlank() && !procesando) }
                item { AyudaButton("Guardar cambios", vm::guardar, habilitado = !procesando) }
            }
            if (error != null) item { Text(error.orEmpty(), color = MaterialTheme.colorScheme.error) }
            if (mensaje != null) item { Text(mensaje.orEmpty(), color = MaterialTheme.colorScheme.primary) }
        }
    }
}
