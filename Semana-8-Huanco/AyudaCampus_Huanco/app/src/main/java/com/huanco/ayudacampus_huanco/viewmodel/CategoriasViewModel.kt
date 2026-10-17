package com.huanco.ayudacampus_huanco.viewmodel

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoriasViewModel(private val repo: AyudaRepository) : ViewModel() {
    private val _cargando = MutableStateFlow(true)
    val cargando = _cargando.asStateFlow()
    val categorias = repo.observarCategorias().onEach { _cargando.value = false }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    private val _procesando = MutableStateFlow(false)
    val procesando = _procesando.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    private val _guardado = MutableStateFlow(false)
    val guardado = _guardado.asStateFlow()

    fun prepararFormulario() {
        _guardado.value = false
        _error.value = null
    }

    fun alternar(c: Categoria) {
        ejecutar { repo.cambiarActiva(c.id, !c.activa) }
    }

    fun guardar(categoria: Categoria) {
        if (categoria.nombre.isBlank()) {
            _error.value = "El nombre es obligatorio"
            return
        }
        ejecutar {
            repo.guardarCategoria(categoria.copy(nombre = categoria.nombre.trim()))
            _guardado.value = true
        }
    }

    fun eliminar(categoria: Categoria) {
        ejecutar {
            if (repo.observarTodosLosTickets().first().any { it.ticket.categoriaId == categoria.id }) {
                _error.value = "No se puede eliminar una categoría que tiene tickets"
            } else {
                repo.eliminarCategoria(categoria)
            }
        }
    }

    private fun ejecutar(accion: suspend () -> Unit) {
        if (_procesando.value) return
        _procesando.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                accion()
            } catch (e: CancellationException) {
                throw e
            } catch (e: SQLiteConstraintException) {
                _error.value = "No se puede eliminar una categoría que tiene tickets"
            } catch (e: Exception) {
                _error.value = "No se pudo completar la operación. Inténtalo nuevamente"
            } finally {
                _procesando.value = false
            }
        }
    }
}
