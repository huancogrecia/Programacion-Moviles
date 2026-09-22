package com.huanco.lab05_clinicasalud_huanco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.huanco.lab05_clinicasalud_huanco.navegacion.NavegacionApp
import com.huanco.lab05_clinicasalud_huanco.ui.theme.Lab05_ClinicaSalud_HuancoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab05_ClinicaSalud_HuancoTheme {
                NavegacionApp()
            }
        }
    }
}