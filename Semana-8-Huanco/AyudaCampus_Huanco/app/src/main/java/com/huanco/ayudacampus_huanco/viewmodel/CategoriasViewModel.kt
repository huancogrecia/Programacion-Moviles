package com.huanco.ayudacampus_huanco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria

class CategoriasViewModel(private val repo: AyudaRepository) : ViewModel() {
val categorias: StateFlow<List<Categoria>> = repo.observarCategorias()
.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
fun alternar(c: Categoria) {
viewModelScope.launch { repo.cambiarActiva(c.id, !c.activa) }
}
// TODO (Paso 11): guardar(categoria) y eliminar(categoria)
}
