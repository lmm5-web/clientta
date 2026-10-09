package com.example.clientta.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = ClienttaPrimary,
    onPrimary = ClienttaWhite,
    primaryContainer = ClienttaComplementary2,
    onPrimaryContainer = ClienttaPrimaryDark,
    secondary = ClienttaPrimary,
    tertiary = ClienttaComplementary,
    background = ClienttaBackground,
    surface = ClienttaWhite,
    onSurface = ClienttaTextPrimary,
    outline = ClienttaBorderGray
)

@Composable
fun ClienttaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}

