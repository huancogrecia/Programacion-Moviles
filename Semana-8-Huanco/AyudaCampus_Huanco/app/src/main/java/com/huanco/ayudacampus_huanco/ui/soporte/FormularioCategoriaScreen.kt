package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
import com.huanco.ayudacampus_huanco.ui.components.AyudaButton
import com.huanco.ayudacampus_huanco.ui.components.AyudaTextField
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FormularioCategoriaScreen(vm: CategoriasViewModel, id: Int? = null, onVolver: () -> Unit) {
    val categorias by vm.categorias.collectAsStateWithLifecycle()
    val cargando by vm.cargando.collectAsStateWithLifecycle()
    val procesando by vm.procesando.collectAsStateWithLifecycle()
    val guardado by vm.guardado.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    var nombre by rememberSaveable(id) { mutableStateOf("") }
    var icono by rememberSaveable(id) { mutableStateOf("pc") }
    var activa by rememberSaveable(id) { mutableStateOf(true) }
    var inicializado by rememberSaveable(id) { mutableStateOf(id == null) }
    var errorNombre by rememberSaveable(id) { mutableStateOf<String?>(null) }
    val categoria = categorias.find { it.id == id }
    val iconos = listOf(
        Triple("pc", "Hardware", Icons.Outlined.Computer),
        Triple("code", "Software", Icons.Outlined.Code),
        Triple("wifi", "Red / Wi-Fi", Icons.Outlined.Wifi),
        Triple("key", "Cuenta", Icons.Outlined.Key),
        Triple("printer", "Impresión", Icons.Outlined.Print),
        Triple("tag", "Etiqueta", Icons.AutoMirrored.Outlined.Label)
    )
    LaunchedEffect(categoria) {
        if (!inicializado && categoria != null) {
            nombre = categoria.nombre
            icono = categoria.icono
            activa = categoria.activa
            inicializado = true
        }
    }
    LaunchedEffect(guardado) { if (guardado) onVolver() }
    LazyColumn(modifier = Modifier.fillMaxSize().imePadding(), contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolver, enabled = !procesando) { Icon(Icons.AutoMirrored.Outlined.ArrowBack, "Volver a categorías") }
                Text(if (id == null) "Nueva categoría" else "Editar categoría", style = MaterialTheme.typography.headlineSmall)
            }
        }
        if (!inicializado) {
            item { Text(if (cargando) "Cargando categoría…" else "Categoría no disponible") }
        } else {
            item {
                AyudaTextField(nombre, { nombre = it; errorNombre = null }, "Nombre de la categoría", error = errorNombre, habilitado = !procesando)
            }
            item {
                Text("Ícono", style = MaterialTheme.typography.titleMedium)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    iconos.forEach { (valor, etiqueta, imagen) ->
                        FilterChip(selected = icono == valor, onClick = { icono = valor }, enabled = !procesando,
                            label = { Text(etiqueta) }, leadingIcon = { Icon(imagen, null) })
                    }
                }
            }
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text("Activa", modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleMedium)
                    Switch(checked = activa, onCheckedChange = { activa = it }, enabled = !procesando)
                }
            }
            item {
                Text("Vista previa", style = MaterialTheme.typography.titleMedium)
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)) {
                    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium) {
                            Icon(iconos.first { it.first == icono }.third, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(12.dp))
                        }
                        Column {
                            Text(nombre.ifBlank { "Nombre de la categoría" }, style = MaterialTheme.typography.titleMedium)
                            Text(if (activa) "Activa" else "Inactiva", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
            if (error != null) item { Text(error.orEmpty(), color = MaterialTheme.colorScheme.error) }
            item {
                AyudaButton("Guardar categoría", {
                    if (nombre.isBlank()) errorNombre = "El nombre es obligatorio"
                    else vm.guardar(Categoria(id = id ?: 0, nombre = nombre, icono = icono, activa = activa))
                }, habilitado = !procesando)
            }
            item { OutlinedButton(onClick = onVolver, enabled = !procesando, modifier = Modifier.fillMaxWidth()) { Text("Cancelar") } }
        }
    }
}
