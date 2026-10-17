package com.huanco.ayudacampus_huanco.viewmodel
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
class AyudaViewModelFactory(private val repo: AyudaRepository, private val preferencias: PreferenciasStore, private val usuario: Usuario? = null, private val ticketId: Int? = null) : ViewModelProvider.Factory {
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
            return NuevoTicketViewModel(repo, requireNotNull(usuario), preferencias) as T
        }
        if (modelClass.isAssignableFrom(TicketDetalleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TicketDetalleViewModel(repo, requireNotNull(usuario), requireNotNull(ticketId)) as T
        }
        if (modelClass.isAssignableFrom(PanelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PanelViewModel(repo) as T
        }
        if (modelClass.isAssignableFrom(BandejaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BandejaViewModel(repo) as T
        }
        if (modelClass.isAssignableFrom(AtenderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AtenderViewModel(repo, requireNotNull(usuario), requireNotNull(ticketId)) as T
        }
        throw IllegalArgumentException("ViewModel no registrado: $modelClass")
    }
}
