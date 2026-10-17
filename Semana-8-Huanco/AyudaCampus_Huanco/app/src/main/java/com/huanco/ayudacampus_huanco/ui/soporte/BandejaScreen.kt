package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.components.AyudaTextField
import com.huanco.ayudacampus_huanco.ui.components.TicketCard
import com.huanco.ayudacampus_huanco.viewmodel.BandejaViewModel

@Composable
fun BandejaScreen(vm: BandejaViewModel, onAtender: (Int) -> Unit) {
    val tickets by vm.tickets.collectAsStateWithLifecycle()
    val estado by vm.estadoSeleccionado.collectAsStateWithLifecycle()
    val prioridad by vm.prioridadSeleccionada.collectAsStateWithLifecycle()
    val categoriaId by vm.categoriaSeleccionada.collectAsStateWithLifecycle()
    val busqueda by vm.textoBusqueda.collectAsStateWithLifecycle()
    val categorias by vm.categorias.collectAsStateWithLifecycle()

    var expandirPrioridad by remember { mutableStateOf(false) }
    var expandirCategoria by remember { mutableStateOf(false) }

    val nombreCategoriaSel = categorias.find { it.id == categoriaId }?.nombre ?: "Todas"

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Tickets", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            AyudaTextField(busqueda, vm::buscar, "Buscar por título o número")
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    "Todos" to null,
                    "Abiertos" to EstadoTicket.ABIERTO,
                    "En proceso" to EstadoTicket.EN_PROCESO,
                    "Resueltos" to EstadoTicket.RESUELTO,
                    "Cerrados" to EstadoTicket.CERRADO
                ).forEach { (titulo, valor) ->
                    FilterChip(
                        selected = estado == valor,
                        onClick = { vm.seleccionarEstado(valor) },
                        label = { Text(titulo) },
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaterialTheme.colorScheme.primary, selectedLabelColor = MaterialTheme.colorScheme.onPrimary)
                    )
                }
            }
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box {
                    FilterChip(
                        selected = prioridad != null,
                        onClick = { expandirPrioridad = true },
                        label = { Text("Prioridad: ${prioridad?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "Todas"}") }
                    )
                    DropdownMenu(expanded = expandirPrioridad, onDismissRequest = { expandirPrioridad = false }) {
                        DropdownMenuItem(
                            text = { Text("Todas") },
                            onClick = { vm.seleccionarPrioridad(null); expandirPrioridad = false }
                        )
                        Prioridad.entries.forEach { p ->
                            DropdownMenuItem(
                                text = { Text(p.name.lowercase().replaceFirstChar { it.uppercase() }) },
                                onClick = { vm.seleccionarPrioridad(p); expandirPrioridad = false }
                            )
                        }
                    }
                }
                Box {
                    FilterChip(
                        selected = categoriaId != null,
                        onClick = { expandirCategoria = true },
                        label = { Text("Categoría: $nombreCategoriaSel") }
                    )
                    DropdownMenu(expanded = expandirCategoria, onDismissRequest = { expandirCategoria = false }) {
                        DropdownMenuItem(
                            text = { Text("Todas") },
                            onClick = { vm.seleccionarCategoria(null); expandirCategoria = false }
                        )
                        categorias.forEach { c ->
                            DropdownMenuItem(
                                text = { Text(c.nombre) },
                                onClick = { vm.seleccionarCategoria(c.id); expandirCategoria = false }
                            )
                        }
                    }
                }
            }
        }
        if (tickets.isEmpty()) {
            Text("No hay tickets con este filtro", Modifier.padding(20.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
            LazyColumn(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(tickets, key = { it.detalle.ticket.id }) { item ->
                    val detalle = item.detalle
                    val ticket = detalle.ticket
                    val minutos = ((System.currentTimeMillis() - ticket.fecha) / 60000).coerceAtLeast(0)
                    val fecha = when {
                        minutos < 60 -> "Hace $minutos min"
                        minutos < 1440 -> "Hace ${minutos / 60} h"
                        minutos < 2880 -> "Ayer"
                        else -> "Hace ${minutos / 1440} d"
                    }
                    TicketCard(
                        id = ticket.id,
                        titulo = ticket.titulo,
                        categoria = detalle.categoria.nombre,
                        iconoCategoria = detalle.categoria.icono,
                        estado = EstadoTicket.valueOf(ticket.estado),
                        prioridad = Prioridad.valueOf(ticket.prioridad),
                        fecha = fecha,
                        onClick = { onAtender(ticket.id) },
                        nombreCliente = detalle.cliente.nombre,
                        slaTexto = item.slaTexto
                    )
                }
            }
        }
    }
}
