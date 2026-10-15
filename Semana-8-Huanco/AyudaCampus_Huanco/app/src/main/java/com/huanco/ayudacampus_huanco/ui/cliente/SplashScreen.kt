package com.huanco.ayudacampus_huanco.ui.cliente
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(onContinuar: () -> Unit) {
    LaunchedEffect(Unit) { delay(1200); onContinuar() }
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(shape = MaterialTheme.shapes.extraLarge, color = MaterialTheme.colorScheme.primaryContainer) {
            Icon(Icons.Outlined.SupportAgent, null, Modifier.padding(24.dp).size(64.dp), tint = MaterialTheme.colorScheme.primary)
        }
        Spacer(Modifier.height(24.dp))
        Text("AyudaCampus", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Text("Mesa de ayuda TI del campus", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(32.dp))
        CircularProgressIndicator(Modifier.size(28.dp))
    }
}
