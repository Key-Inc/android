package com.keyinc.keymono.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun KeyMonoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}


private val DatePickerPallet = lightColorScheme(
    primary = LightBlue,
    onPrimary = Color.White,
    secondary = LightBlue,
    onSecondary = Color.White,
    error = Red,
    onError = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
)


@Composable
fun DatePickerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
    ) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                primary = DatePickerPallet.primary,
                primaryContainer = DatePickerPallet.primary,
                secondary = DatePickerPallet.primary,
                secondaryContainer = DatePickerPallet.primary,
                tertiary = DatePickerPallet.secondary,
                tertiaryContainer = DatePickerPallet.secondary,
                onPrimary = DatePickerPallet.onPrimary,
                onSecondary = DatePickerPallet.onPrimary,
                onTertiary = DatePickerPallet.onSecondary,
                error = DatePickerPallet.error,
                onError = DatePickerPallet.onError,
                surface = DatePickerPallet.surface,
                background = DatePickerPallet.background
            ),
            typography = DatePickerTypography,
            content = content
        )
    }
}








