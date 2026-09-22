package com.huanco.tecsupfit_huanco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.huanco.tecsupfit_huanco.pantallas.PantallaInicio
import com.huanco.tecsupfit_huanco.ui.theme.TECSUPfit_HuancoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TECSUPfit_HuancoTheme {
                PantallaInicio()
            }
        }
    }
}