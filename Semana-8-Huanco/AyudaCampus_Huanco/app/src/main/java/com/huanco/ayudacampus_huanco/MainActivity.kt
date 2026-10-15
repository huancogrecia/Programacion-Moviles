package com.huanco.ayudacampus_huanco
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.huanco.ayudacampus_huanco.model.Rol
import com.huanco.ayudacampus_huanco.ui.navigation.NavAyuda
import com.huanco.ayudacampus_huanco.ui.theme.AyudaTheme
import com.huanco.ayudacampus_huanco.viewmodel.AyudaViewModelFactory
import com.huanco.ayudacampus_huanco.viewmodel.CategoriasViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val factory = AyudaViewModelFactory((application as AyudaApp).repository)
        setContent {
            AyudaTheme(Rol.ADMIN) {
                val vm: CategoriasViewModel = viewModel(factory = factory)
                NavAyuda(vm)
            }
        }
    }
}
