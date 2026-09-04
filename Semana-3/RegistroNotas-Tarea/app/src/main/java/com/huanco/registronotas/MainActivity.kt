package com.huanco.registronotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huanco.registronotas.ui.theme.RegistroNotasTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RegistroNotasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaRegistroNotas(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaRegistroNotas(modifier: Modifier = Modifier) {

    var notaFundamentos by remember { mutableStateOf(0f) }
    var notaPoo by remember { mutableStateOf(0f) }
    var notaMoviles by remember { mutableStateOf(0f) }
    var notaBaseDatos by remember { mutableStateOf(0f) }

    var redondearPromedio by remember { mutableStateOf(false) }
    var notasConfirmadas by remember { mutableStateOf(false) }
    var promedioFinal by remember { mutableStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Registro de Notas",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Notas del ciclo",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Desliza para asignar cada nota (0 a 20)",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Fundamentos de Programación (20%)",
                modifier = Modifier.weight(1f)
            )

            Text(
                text = notaFundamentos.toInt().toString()
            )
        }

        Slider(
            value = notaFundamentos,
            onValueChange = { notaFundamentos = it },
            valueRange = 0f..20f,
            steps = 19,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Programación Orientada a Objetos (25%)",
                modifier = Modifier.weight(1f)
            )

            Text(
                text = notaPoo.toInt().toString()
            )
        }

        Slider(
            value = notaPoo,
            onValueChange = { notaPoo = it },
            valueRange = 0f..20f,
            steps = 19,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Programación en Móviles (30%)",
                modifier = Modifier.weight(1f)
            )

            Text(
                text = notaMoviles.toInt().toString()
            )
        }

        Slider(
            value = notaMoviles,
            onValueChange = { notaMoviles = it },
            valueRange = 0f..20f,
            steps = 19,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Base de Datos (25%)",
                modifier = Modifier.weight(1f)
            )

            Text(
                text = notaBaseDatos.toInt().toString()
            )
        }

        Slider(
            value = notaBaseDatos,
            onValueChange = { notaBaseDatos = it },
            valueRange = 0f..20f,
            steps = 19,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Redondear promedio final",
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = redondearPromedio,
                onCheckedChange = { redondearPromedio = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = notasConfirmadas,
                onCheckedChange = { notasConfirmadas = it }
            )

            Text(
                text = "Confirmo que las notas son correctas"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                promedioFinal =
                    notaFundamentos * 0.20f +
                            notaPoo * 0.25f +
                            notaMoviles * 0.30f +
                            notaBaseDatos * 0.25f },
            enabled = notasConfirmadas,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "CALCULAR PROMEDIO")
        }

        val promedioEvaluado = if (redondearPromedio) {
            promedioFinal.roundToInt().toFloat()
        } else {
            promedioFinal
        }

        val observacion = when {
            promedioEvaluado >= 17 -> "EXCELENTE"
            promedioEvaluado >= 13 -> "APROBADO"
            promedioEvaluado >= 10 -> "EN RECUPERACIÓN"
            else -> "DESAPROBADO"
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (redondearPromedio) {
                "Promedio final: ${promedioFinal.roundToInt()}"
            } else {
                "Promedio final: %.2f".format(promedioFinal)
            }
        )

        Text(
            text = "Observación: $observacion"
        )
    }
}