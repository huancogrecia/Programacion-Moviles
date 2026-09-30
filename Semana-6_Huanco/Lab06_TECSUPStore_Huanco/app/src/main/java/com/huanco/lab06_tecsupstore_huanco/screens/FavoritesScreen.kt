package com.huanco.lab06_tecsupstore_huanco.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huanco.lab06_tecsupstore_huanco.components.ProductCard
import com.huanco.lab06_tecsupstore_huanco.model.Product

@Composable
fun FavoritesScreen(
    paddingValues: PaddingValues,
    listaFavoritos: List<Product> = emptyList(),
    onToggleFavorite: (Product) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (listaFavoritos.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No tienes productos guardados en favoritos.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaFavoritos) { producto ->
                    ProductCard(
                        product = producto,
                        isFavorite = true,
                        onFavoriteClick = { onToggleFavorite(producto) }
                    )
                }
            }
        }
    }
}