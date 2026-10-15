package com.huanco.ayudacampus_huanco.ui.components
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(titulo: String) {
    TopAppBar(title = { Text(titulo) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background))
}
