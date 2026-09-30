package com.huanco.lab06_tecsupstore_huanco.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.huanco.lab06_tecsupstore_huanco.navegacion.Rutas

@Composable
fun AppDrawer(
    rutaActual: String,
    cantidadFavoritos: Int = 0,
    onInicioClick: () -> Unit,
    onPedidosClick: () -> Unit,
    onFavoritosClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "GM",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Grecia Milagros Huanco Ticona",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Cliente",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }

        HorizontalDivider()

        Spacer(modifier = Modifier.height(8.dp))

        NavigationDrawerItem(
            label = {
                Text("Inicio")
            },
            selected = rutaActual == Rutas.INICIO,
            onClick = onInicioClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            }
        )

        NavigationDrawerItem(
            label = {
                Text("Mis pedidos")
            },
            selected = rutaActual == Rutas.PEDIDOS,
            onClick = onPedidosClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            }
        )

        NavigationDrawerItem(
            label = {
                Text("Favoritos")
            },
            selected = rutaActual == Rutas.FAVORITOS,
            onClick = onFavoritosClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            },
            badge = {
                if (cantidadFavoritos > 0) {
                    Badge {
                        Text(text = cantidadFavoritos.toString())
                    }
                }
            }
        )

        NavigationDrawerItem(
            label = {
                Text("Perfil")
            },
            selected = rutaActual == Rutas.PERFIL,
            onClick = onPerfilClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        )
    }
}