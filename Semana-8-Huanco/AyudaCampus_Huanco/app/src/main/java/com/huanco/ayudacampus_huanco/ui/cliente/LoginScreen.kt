package com.huanco.ayudacampus_huanco.ui.cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.huanco.ayudacampus_huanco.model.Rol
import com.huanco.ayudacampus_huanco.ui.components.AyudaButton
import com.huanco.ayudacampus_huanco.ui.components.AyudaTextField
import com.huanco.ayudacampus_huanco.viewmodel.SesionViewModel

@Composable
fun LoginScreen(vm: SesionViewModel, rol: Rol, onRolChange: (Rol) -> Unit) {
    var correo by rememberSaveable { mutableStateOf("") }
    var clave by rememberSaveable { mutableStateOf("") }
    val error by vm.error.collectAsStateWithLifecycle()
    val ingresando by vm.ingresando.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .imePadding().verticalScroll(rememberScrollState()).padding(24.dp)
    ) {
        Spacer(Modifier.height(40.dp))
        Text("Bienvenido", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Ingresa para continuar", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp)).padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            listOf(Rol.CLIENTE, Rol.ADMIN).forEach { perfil ->
                val seleccionado = rol == perfil
                Surface(
                    onClick = { onRolChange(perfil) },
                    enabled = !ingresando,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = if (seleccionado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (seleccionado) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    Text(
                        text = if (perfil == Rol.CLIENTE) "Estudiante" else "Soporte TI",
                        modifier = Modifier.padding(vertical = 14.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        Spacer(Modifier.height(28.dp))
        AyudaTextField(correo, { correo = it }, "Correo", habilitado = !ingresando, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
        Spacer(Modifier.height(12.dp))
        AyudaTextField(clave, { clave = it }, "Contraseña", habilitado = !ingresando, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), visualTransformation = PasswordVisualTransformation())
        if (error != null) {
            Spacer(Modifier.height(12.dp))
            Text(error.orEmpty(), color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(32.dp))
        AyudaButton("Ingresar", { vm.login(correo, clave, rol) }, modifier = Modifier.height(56.dp), habilitado = !ingresando)
    }
}
