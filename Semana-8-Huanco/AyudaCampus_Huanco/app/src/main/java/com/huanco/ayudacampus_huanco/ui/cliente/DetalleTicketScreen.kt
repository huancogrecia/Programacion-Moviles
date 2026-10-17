package com.huanco.ayudacampus_huanco.ui.cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.components.AyudaButton
import com.huanco.ayudacampus_huanco.ui.components.AyudaTextField
import com.huanco.ayudacampus_huanco.ui.components.BurbujaComentario
import com.huanco.ayudacampus_huanco.ui.components.EstadoChip
import com.huanco.ayudacampus_huanco.ui.components.Estrellas
import com.huanco.ayudacampus_huanco.ui.components.PrioridadChip
import com.huanco.ayudacampus_huanco.ui.components.StepperTicket
import com.huanco.ayudacampus_huanco.ui.theme.EstadoResuelto
import com.huanco.ayudacampus_huanco.viewmodel.TicketDetalleViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetalleTicketScreen(vm: TicketDetalleViewModel, onVolver: () -> Unit) {
    val detalle by vm.ticket.collectAsStateWithLifecycle()
    val cargando by vm.cargando.collectAsStateWithLifecycle()
    val texto by vm.textoComentario.collectAsStateWithLifecycle()
    val comentarioFinal by vm.comentarioFinal.collectAsStateWithLifecycle()
    val procesando by vm.procesando.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    val slaTexto by vm.slaTexto.collectAsStateWithLifecycle()
    var calificacion by rememberSaveable { mutableIntStateOf(0) }
    val actual = detalle?.takeIf { it.ticket.clienteId == vm.usuario.id }
    LazyColumn(
        modifier = Modifier.fillMaxSize().imePadding(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolver, enabled = !procesando) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Volver a Mis tickets")
                }
                Text(actual?.let { "Ticket #${it.ticket.id}" } ?: "Detalle del ticket", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
        }
        if (actual == null) {
            item { Text(if (cargando) "Cargando ticket…" else "Ticket no disponible") }
        } else {
            val ticket = actual.ticket
            val estado = EstadoTicket.valueOf(ticket.estado)
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
                    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(ticket.titulo, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            EstadoChip(estado)
                            PrioridadChip(Prioridad.valueOf(ticket.prioridad))
                        }
                        Text("${actual.categoria.nombre} · ${ticket.ubicacion}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        if (slaTexto.isNotBlank()) {
                            Text(if (slaTexto == "SLA vencido") slaTexto else "SLA: $slaTexto", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Text(ticket.descripcion)
                    }
                }
            }
            item { StepperTicket(estado) }
            if (estado == EstadoTicket.RESUELTO) {
                item {
                    Surface(shape = MaterialTheme.shapes.large, color = EstadoResuelto.copy(alpha = 0.12f)) {
                        Text("Soporte marcó este ticket como resuelto. ¿El problema se solucionó?", Modifier.fillMaxWidth().padding(16.dp), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
            item { Text("Conversación", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold) }
            if (actual.comentarios.isEmpty()) {
                item { Text("Aún no hay comentarios", color = MaterialTheme.colorScheme.onSurfaceVariant) }
            }
            items(actual.comentarios.sortedWith(compareBy({ it.fecha }, { it.id })), key = { "comentario_${it.id}" }) { comentario ->
                BurbujaComentario(
                    autorNombre = comentario.autorNombre,
                    texto = comentario.texto,
                    fecha = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()).format(Date(comentario.fecha)),
                    esPropio = comentario.autorId == vm.usuario.id
                )
            }
            if (estado != EstadoTicket.CERRADO) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        AyudaTextField(texto, vm::cambiarComentario, "Escribe un comentario", habilitado = !procesando, unaLinea = false)
                        AyudaButton("Enviar comentario", { vm.comentar(texto) }, habilitado = texto.isNotBlank() && !procesando)
                    }
                }
            }
            if (estado == EstadoTicket.RESUELTO) {
                item {
                    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
                        Column(Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Califica la atención", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(12.dp))
                            Estrellas(calificacion, onCalificar = { calificacion = it }, habilitado = !procesando)
                            Text("$calificacion de 5 estrellas", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
                item { AyudaTextField(comentarioFinal, vm::cambiarComentarioFinal, "Comentario final (opcional)", habilitado = !procesando, unaLinea = false) }
                item { AyudaButton("Confirmar y cerrar", { vm.cerrar(calificacion) }, habilitado = calificacion in 1..5 && !procesando) }
                item {
                    OutlinedButton(onClick = vm::reabrir, modifier = Modifier.fillMaxWidth(), enabled = !procesando) {
                        Text("Reabrir ticket")
                    }
                }
            }
            if (estado == EstadoTicket.CERRADO) {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Text("Calificación recibida", style = MaterialTheme.typography.titleMedium)
                        Estrellas(ticket.calificacion ?: 0)
                        Text("${ticket.calificacion ?: 0} de 5 estrellas")
                    }
                }
            }
            if (error != null) {
                item { Text(error.orEmpty(), color = MaterialTheme.colorScheme.error) }
            }
        }
    }
}
