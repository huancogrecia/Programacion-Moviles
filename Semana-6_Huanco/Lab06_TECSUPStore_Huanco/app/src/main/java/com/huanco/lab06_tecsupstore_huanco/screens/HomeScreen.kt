package com.huanco.lab06_tecsupstore_huanco.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huanco.lab06_tecsupstore_huanco.components.ProductCard
import com.huanco.lab06_tecsupstore_huanco.model.Product

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    val categorias = listOf(
        "Todos",
        "Tecnología",
        "Accesorios"
    )

    val productos = listOf(
        Product(
            1,
            "Mouse inalámbrico",
            59.90,
            "Tecnología"
        ),
        Product(
            2,
            "Teclado mecánico",
            129.90,
            "Tecnología"
        ),
        Product(
            3,
            "Audífonos",
            89.90,
            "Accesorios"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text(text = "Categorías")

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categorias) { categoria ->
                Button(
                    onClick = { }
                ) {
                    Text(text = categoria)
                }
            }
        }

        Text(text = "Productos")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productos) { producto ->
                ProductCard(product = producto)
            }
        }
    }
}