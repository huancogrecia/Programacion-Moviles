package com.huanco.lab06_tecsupstore_huanco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.huanco.lab06_tecsupstore_huanco.screens.HomeScreen
import com.huanco.lab06_tecsupstore_huanco.ui.theme.Lab06_TECSUPStore_HuancoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab06_TECSUPStore_HuancoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}