package com.huanco.ayudacampus_huanco.ui.soporte

import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel

@Composable
fun CategoriasScreen(vm: CategoriasViewModel, onEditar: (Int) -> Unit) {
val categorias by vm.categorias.collectAsState()
LazyColumn {
items(categorias, key = { it.id }) { c ->
CategoriaItem(c, onToggle = { vm.alternar(c) }, onEditar = { onEditar(c.id) })
}
}
}
