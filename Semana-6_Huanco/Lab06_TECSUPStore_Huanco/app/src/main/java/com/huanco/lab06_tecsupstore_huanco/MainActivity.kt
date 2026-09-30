package com.huanco.lab06_tecsupstore_huanco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.huanco.lab06_tecsupstore_huanco.navegacion.AppNavegacion
import com.huanco.lab06_tecsupstore_huanco.ui.theme.Lab06_TECSUPStore_HuancoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab06_TECSUPStore_HuancoTheme {
                AppNavegacion()
            }
        }
    }
}