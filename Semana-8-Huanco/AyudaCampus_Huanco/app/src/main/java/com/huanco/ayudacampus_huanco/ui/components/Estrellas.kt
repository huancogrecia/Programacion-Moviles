package com.huanco.ayudacampus_huanco.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun Estrellas(
    calificacion: Int,
    modifier: Modifier = Modifier,
    onCalificar: ((Int) -> Unit)? = null,
    habilitado: Boolean = true
) {
    Row(modifier = modifier.selectableGroup()) {
        for (valor in 1..5) {
            val seleccion = if (onCalificar != null) Modifier.selectable(
                selected = calificacion == valor,
                enabled = habilitado,
                role = Role.RadioButton,
                onClick = { onCalificar(valor) }
            ) else Modifier
            Icon(
                imageVector = if (valor <= calificacion) Icons.Outlined.Star
                    else Icons.Outlined.StarBorder,
                contentDescription = "$valor de 5 estrellas",
                modifier = Modifier.size(48.dp).then(seleccion),
                tint = if (habilitado || onCalificar == null) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
