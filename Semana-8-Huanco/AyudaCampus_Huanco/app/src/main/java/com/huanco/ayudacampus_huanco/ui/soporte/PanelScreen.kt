package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.ui.components.graficos.GraficoBarras
import com.huanco.ayudacampus_huanco.ui.components.graficos.GraficoDona
import com.huanco.ayudacampus_huanco.ui.theme.EstadoAbierto
import com.huanco.ayudacampus_huanco.ui.theme.EstadoEnProceso
import com.huanco.ayudacampus_huanco.ui.theme.EstadoResuelto
import com.huanco.ayudacampus_huanco.viewmodel.PanelViewModel

@Composable
fun PanelScreen(vm: PanelViewModel, onVerTickets: () -> Unit) {
    val abiertos by vm.abiertos.collectAsStateWithLifecycle()
    val enProceso by vm.enProceso.collectAsStateWithLifecycle()
    val resueltos by vm.resueltos.collectAsStateWithLifecycle()
    val porEstado by vm.porEstado.collectAsStateWithLifecycle()
    val porCategoria by vm.porCategoria.collectAsStateWithLifecycle()
    val altas by vm.altasSinAtender.collectAsStateWithLifecycle()
    val tiempoPromedio by vm.tiempoPromedioResolucion.collectAsStateWithLifecycle()
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Panel de soporte", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Text("Mesa de ayuda TI", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    Triple("Abiertos", abiertos, EstadoAbierto),
                    Triple("En proceso", enProceso, EstadoEnProceso),
                    Triple("Resueltos", resueltos, EstadoResuelto)
                ).forEach { (titulo, cantidad, color) ->
                    Card(modifier = Modifier.weight(1f), colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.12f))) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(cantidad.toString(), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Text(titulo, style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Tiempo promedio de resolución", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(tiempoPromedio, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
        item { Text("Tickets por estado", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold) }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
                GraficoDona(porEstado, Modifier.padding(16.dp))
            }
        }
        item { Text("Tickets por categoría", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold) }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
                GraficoBarras(porCategoria, Modifier.padding(16.dp))
            }
        }
        if (altas > 0) {
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(if (altas == 1) "1 ticket de prioridad Alta" else "$altas tickets de prioridad Alta", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onErrorContainer)
                        Text("Sin atender hace más de 1 hora", color = MaterialTheme.colorScheme.onErrorContainer)
                        Button(onClick = onVerTickets, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error, contentColor = MaterialTheme.colorScheme.onError)) {
                            Text("Ver")
                        }
                    }
                }
            }
        }
    }
}
