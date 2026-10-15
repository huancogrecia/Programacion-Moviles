package com.huanco.ayudacampus_huanco.ui.cliente

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.components.AyudaTextField
import com.huanco.ayudacampus_huanco.ui.components.TicketCard
import com.huanco.ayudacampus_huanco.viewmodel.MisTicketsViewModel

@Composable
fun MisTicketsScreen(vm: MisTicketsViewModel, onDetalle: (Int) -> Unit, onNuevo: () -> Unit) {
    val tickets by vm.tickets.collectAsStateWithLifecycle()
    val abiertos by vm.cantidadAbiertos.collectAsStateWithLifecycle()
    val estado by vm.estadoSeleccionado.collectAsStateWithLifecycle()
    val busqueda by vm.textoBusqueda.collectAsStateWithLifecycle()
    val nombre = vm.usuario.nombre.split(" ").take(2).joinToString(" ")
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNuevo, containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary) {
                Icon(Icons.Outlined.Add, contentDescription = "Crear ticket")
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text("Mis tickets", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Hola, $nombre · $abiertos abiertos", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(20.dp))
                AyudaTextField(busqueda, vm::buscar, "Buscar por título o número")
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(
                        "Todos" to null,
                        "Abiertos" to EstadoTicket.ABIERTO,
                        "En proceso" to EstadoTicket.EN_PROCESO,
                        "Resueltos" to EstadoTicket.RESUELTO
                    ).forEach { (nombreFiltro, valor) ->
                        FilterChip(
                            selected = estado == valor,
                            onClick = { vm.seleccionarEstado(valor) },
                            label = { Text(nombreFiltro) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
            if (tickets.isEmpty()) {
                Text("No tienes tickets aquí", Modifier.padding(20.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                LazyColumn(contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 96.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(tickets, key = { it.ticket.id }) { detalle ->
                        val ticket = detalle.ticket
                        val horas = ((System.currentTimeMillis() - ticket.fecha) / 3600000).coerceAtLeast(0)
                        val fecha = when {
                            horas == 0L -> "Hace un momento"
                            horas < 24 -> "Hace $horas h"
                            horas < 48 -> "Ayer"
                            else -> "Hace ${horas / 24} d"
                        }
                        TicketCard(
                            id = ticket.id,
                            titulo = ticket.titulo,
                            categoria = ticket.ubicacion,
                            iconoCategoria = detalle.categoria.icono,
                            estado = EstadoTicket.valueOf(ticket.estado),
                            prioridad = Prioridad.valueOf(ticket.prioridad),
                            fecha = fecha,
                            onClick = { onDetalle(ticket.id) }
                        )
                    }
                }
            }
        }
    }
}
