package com.huanco.contadorsinremember

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ContadorRoto()
            }
        }
    }
}

@Composable
fun ContadorRoto() {
    var contador = 0 // se resetea a 0 en cada recomposición

    Column {
        Text("Contador: $contador")

        Button(onClick = { contador++ }) { // No causa recomposición
            Text("Incrementar")
        }
    }
}