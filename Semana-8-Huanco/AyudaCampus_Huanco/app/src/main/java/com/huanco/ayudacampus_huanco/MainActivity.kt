package com.huanco.ayudacampus_huanco
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.huanco.ayudacampus_huanco.data.prefs.PreferenciasStore
import com.huanco.ayudacampus_huanco.ui.navigation.NavAyuda
import com.huanco.ayudacampus_huanco.viewmodel.AyudaViewModelFactory
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel
import com.huanco.ayudacampus_huanco.viewmodel.SesionViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val factory = AyudaViewModelFactory((application as AyudaApp).repository, PreferenciasStore(this))
        setContent {
            val vm: CategoriasViewModel = viewModel(factory = factory)
            val sesion: SesionViewModel = viewModel(factory = factory)
            NavAyuda(vm, sesion)
        }
    }
}
