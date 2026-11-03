package com.croisenature.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = ForestGreen,
    onPrimary = Color.White,
    primaryContainer = SageGreen,
    onPrimaryContainer = DeepForestGreen,
    secondary = EarthBrown,
    onSecondary = Color.White,
    tertiary = SunYellow,
    background = NatureBeige,
    onBackground = BarkBrown,
    surface = Color.White,
    onSurface = BarkBrown
)

private val DarkColors = darkColorScheme(
    primary = SageGreen,
    onPrimary = DeepForestGreen,
    primaryContainer = ForestGreen,
    onPrimaryContainer = NatureBeige,
    secondary = EarthBrown,
    onSecondary = NatureBeige,
    tertiary = SunYellow,
    background = DarkEarth,
    onBackground = NatureBeige,
    surface = Color(0xFF37474F),
    onSurface = NatureBeige
)

@Composable
fun CroiseNatureTheme(
    darkTheme: Boolean = false, // On force le thème clair (Nature) par défaut
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
