package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel

@Composable
fun CategoriasScreen(vm: CategoriasViewModel, onEditar: (Int) -> Unit, onNueva: () -> Unit) {
    val categorias by vm.categorias.collectAsStateWithLifecycle()
    val procesando by vm.procesando.collectAsStateWithLifecycle()
    val error by vm.error.collectAsStateWithLifecycle()
    LazyColumn {
        item {
            Button(onClick = onNueva, enabled = !procesando, modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) { Text("Nueva categoría") }
        }
        if (error != null) item { Text(error.orEmpty(), Modifier.padding(20.dp), color = MaterialTheme.colorScheme.error) }
        items(categorias, key = { it.id }) { c ->
            CategoriaItem(c, onToggle = { vm.alternar(c) }, onEditar = { onEditar(c.id) }, onEliminar = { vm.eliminar(c) }, habilitado = !procesando)
        }
    }
}
