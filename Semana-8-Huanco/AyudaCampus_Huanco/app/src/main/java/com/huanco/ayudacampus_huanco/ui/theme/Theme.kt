package com.huanco.ayudacampus_huanco.ui.theme
import android.app.Activity
import androidx.core.view.WindowCompat
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.graphics.Color
import com.huanco.ayudacampus_huanco.model.Rol
@Composable
fun AyudaTheme(rol: Rol, modoOscuro: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val soporte = rol == Rol.ADMIN
    val scheme = if (modoOscuro) darkColorScheme(
        primary = if (soporte) Color(0xFFD0BCFF) else Color(0xFF67E8F9),
        primaryContainer = if (soporte) Color(0xFF4C1D95) else Color(0xFF164E63)
    ) else lightColorScheme(
        primary = if (soporte) Violeta else Cian,
        onPrimary = Color.White,
        primaryContainer = if (soporte) VioletaContenedor else CianContenedor,
        onPrimaryContainer = if (soporte) Color(0xFF3B0764) else Color(0xFF164E63),
        background = if (soporte) FondoSoporte else FondoCliente,
        surface = if (soporte) FondoSoporte else FondoCliente
    )
    val view = LocalView.current
    if (!view.isInEditMode) {
        val activity = view.context as? Activity
        SideEffect {
            if (activity != null) {
                val controller = WindowCompat.getInsetsController(activity.window, view)
                controller.isAppearanceLightStatusBars = !modoOscuro
                controller.isAppearanceLightNavigationBars = !modoOscuro
            }
        }
    }
    MaterialTheme(colorScheme = scheme, typography = Typography, content = content)
}
@Composable
fun AyudaCampus_HuancoTheme(content: @Composable () -> Unit) { AyudaTheme(Rol.ADMIN, content = content) }
