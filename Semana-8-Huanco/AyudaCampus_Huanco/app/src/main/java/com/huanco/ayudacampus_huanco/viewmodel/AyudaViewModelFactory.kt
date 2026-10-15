package com.huanco.ayudacampus_huanco.viewmodel
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
class AyudaViewModelFactory(private val repo: AyudaRepository, private val preferencias: PreferenciasStore, private val usuario: Usuario? = null) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriasViewModel(repo) as T
        }
        if (modelClass.isAssignableFrom(SesionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SesionViewModel(repo, preferencias) as T
        }
        if (modelClass.isAssignableFrom(MisTicketsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MisTicketsViewModel(repo, requireNotNull(usuario)) as T
        }
        if (modelClass.isAssignableFrom(NuevoTicketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NuevoTicketViewModel(repo, requireNotNull(usuario)) as T
        }
        throw IllegalArgumentException("ViewModel no registrado: $modelClass")
    }
}
