package com.huanco.ayudacampus_huanco.ui.cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.ui.components.AyudaButton
import com.huanco.ayudacampus_huanco.ui.components.AyudaTextField
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadAlta
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadBaja
import com.huanco.ayudacampus_huanco.ui.theme.PrioridadMedia
import com.huanco.ayudacampus_huanco.viewmodel.NuevoTicketViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NuevoTicketScreen(vm: NuevoTicketViewModel, onVolver: () -> Unit, onEnviado: () -> Unit) {
    val categorias by vm.categorias.collectAsStateWithLifecycle()
    val categoriaId by vm.categoriaId.collectAsStateWithLifecycle()
    val titulo by vm.titulo.collectAsStateWithLifecycle()
    val ubicacion by vm.ubicacion.collectAsStateWithLifecycle()
    val prioridad by vm.prioridad.collectAsStateWithLifecycle()
    val descripcion by vm.descripcion.collectAsStateWithLifecycle()
    val errorCategoria by vm.errorCategoria.collectAsStateWithLifecycle()
    val errorTitulo by vm.errorTitulo.collectAsStateWithLifecycle()
    val errorDescripcion by vm.errorDescripcion.collectAsStateWithLifecycle()
    val errorEnvio by vm.errorEnvio.collectAsStateWithLifecycle()
    val enviando by vm.enviando.collectAsStateWithLifecycle()
    val enviado by vm.enviado.collectAsStateWithLifecycle()
    LaunchedEffect(enviado) {
        if (enviado) onEnviado()
    }
    Column(Modifier.fillMaxSize().imePadding().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onVolver, enabled = !enviando) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Volver a Mis tickets")
            }
            Text("Nuevo ticket", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(20.dp))
        Text("Categoría", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categorias.forEach { categoria ->
                val icono = when (categoria.icono) {
                    "pc" -> Icons.Outlined.Computer
                    "code" -> Icons.Outlined.Code
                    "wifi" -> Icons.Outlined.Wifi
                    "key" -> Icons.Outlined.Key
                    "printer" -> Icons.Outlined.Print
                    else -> Icons.AutoMirrored.Outlined.Label
                }
                FilterChip(
                    selected = categoriaId == categoria.id,
                    onClick = { vm.seleccionarCategoria(categoria.id) },
                    enabled = !enviando,
                    label = { Text(categoria.nombre) },
                    leadingIcon = { Icon(icono, contentDescription = null) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
        if (errorCategoria != null) {
            Text(errorCategoria.orEmpty(), color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(Modifier.height(12.dp))
        AyudaTextField(titulo, vm::cambiarTitulo, "Título", error = errorTitulo, habilitado = !enviando)
        Spacer(Modifier.height(12.dp))
        AyudaTextField(ubicacion, vm::cambiarUbicacion, "Ubicación", habilitado = !enviando)
        Spacer(Modifier.height(16.dp))
        Text("Prioridad", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(Prioridad.BAJA to "Baja", Prioridad.MEDIA to "Media", Prioridad.ALTA to "Alta").forEach { (valor, etiqueta) ->
                val color = when (valor) {
                    Prioridad.BAJA -> PrioridadBaja
                    Prioridad.MEDIA -> PrioridadMedia
                    Prioridad.ALTA -> PrioridadAlta
                }
                FilterChip(
                    selected = prioridad == valor,
                    onClick = { vm.seleccionarPrioridad(valor) },
                    enabled = !enviando,
                    modifier = Modifier.weight(1f),
                    label = { Text(etiqueta) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = color,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        AyudaTextField(descripcion, vm::cambiarDescripcion, "Descripción", modifier = Modifier.heightIn(min = 140.dp), error = errorDescripcion, habilitado = !enviando, unaLinea = false)
        if (errorEnvio != null) {
            Spacer(Modifier.height(12.dp))
            Text(errorEnvio.orEmpty(), color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(24.dp))
        AyudaButton("Enviar ticket", vm::enviar, modifier = Modifier.height(56.dp), habilitado = !enviando && !enviado)
        Spacer(Modifier.height(12.dp))
    }
}
