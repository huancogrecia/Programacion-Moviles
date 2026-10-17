package com.huanco.ayudacampus_huanco.ui.soporte
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
@Composable
fun CategoriaItem(c: Categoria, onToggle: () -> Unit, onEditar: () -> Unit, onEliminar: () -> Unit, habilitado: Boolean = true) {
    val icono = when (c.icono) {
        "pc" -> Icons.Outlined.Computer
        "code" -> Icons.Outlined.Code
        "wifi" -> Icons.Outlined.Wifi
        "key" -> Icons.Outlined.Key
        "printer" -> Icons.Outlined.Print
        else -> Icons.AutoMirrored.Outlined.Label
    }
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                Icon(icono, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(12.dp))
            }
            Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                Text(c.nombre, style = MaterialTheme.typography.titleMedium)
                Text(if (c.activa) "Activa" else "Inactiva", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Switch(checked = c.activa, onCheckedChange = { onToggle() }, enabled = habilitado)
        }
        Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = onEditar, enabled = habilitado) { Text("Editar") }
            TextButton(onClick = onEliminar, enabled = habilitado) { Text("Eliminar", color = MaterialTheme.colorScheme.error) }
        }
    }
}
