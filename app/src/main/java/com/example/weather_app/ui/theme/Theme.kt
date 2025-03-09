package com.example.weather_app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val bluePrimeBackground = Color(0xFF415F91)
val lightBlueSecondBars = Color(0xFFd6e3ff)
val lightBlueThird  =Color(0xFFdae2f9)
val greyInfoBox = Color(0xFF565f71)
val submitButton = Color(0xFF705575)

private val lightColorPalette = lightColorScheme(
    primary = Color(0xFF0288D1),
    primaryContainer = lightBlueThird,
    secondary = greyInfoBox,
    background = bluePrimeBackground,
    surface = submitButton,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = lightBlueSecondBars,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val darkColorPalette = darkColorScheme(
    primary = Color(0xFF0288D1),
    primaryContainer = Color.White,
    secondary = Color(0xFF03A9F4),
    background = bluePrimeBackground,
    surface = submitButton,
    error = Color(0xFFCF6679),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)


@Composable
fun weather_AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorPalette
        else -> lightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}

