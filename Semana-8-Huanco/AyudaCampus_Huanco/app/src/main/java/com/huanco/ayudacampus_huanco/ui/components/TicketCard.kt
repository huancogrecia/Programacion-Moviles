package com.huanco.ayudacampus_huanco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadAlta
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadMedia
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadBaja

@Composable
fun TicketCard(
    id: Int,
    titulo: String,
    categoria: String,
    iconoCategoria: String,
    estado: EstadoTicket,
    prioridad: Prioridad,
    fecha: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    nombreCliente: String? = null
) {
    val colorPrioridad = when (prioridad) {
        Prioridad.ALTA -> PrioridadAlta
        Prioridad.MEDIA -> PrioridadMedia
        Prioridad.BAJA -> PrioridadBaja
    }
    val icono = when (iconoCategoria) {
        "pc" -> Icons.Outlined.Computer
        "code" -> Icons.Outlined.Code
        "wifi" -> Icons.Outlined.Wifi
        "key" -> Icons.Outlined.Key
        "printer" -> Icons.Outlined.Print
        else -> Icons.AutoMirrored.Outlined.Label
    }
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        )
    ) {
        Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            Box(Modifier.width(4.dp).fillMaxHeight().background(colorPrioridad))
            Column(
                modifier = Modifier.weight(1f).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("#$id", style = MaterialTheme.typography.labelMedium)
                    Text(fecha, style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text(titulo, style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold, maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(icono, contentDescription = null, modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary)
                    Text(categoria, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                if (!nombreCliente.isNullOrBlank()) {
                    Text(nombreCliente, style = MaterialTheme.typography.bodySmall)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    EstadoChip(estado)
                    PrioridadChip(prioridad)
                }
            }
        }
    }
}
