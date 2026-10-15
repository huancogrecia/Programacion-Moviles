package com.huanco.ayudacampus_huanco.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
class AyudaViewModelFactory(private val repo: AyudaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriasViewModel(repo) as T
        }
        throw IllegalArgumentException("ViewModel no registrado: $modelClass")
    }
}
