package com.fps.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LightColorScheme = lightColorScheme(
    primary = StoiximanGray,
    background = StoiximanWhite,
    surface = StoiximanWhite,
    secondary = StoiximanGray,
    primaryContainer = StoiximanBlue,
    onPrimary = Color.Black,
    onBackground = StoiximanWhite,
    onSurface = StoiximanWhite
)

@Composable
fun StoiximanTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}