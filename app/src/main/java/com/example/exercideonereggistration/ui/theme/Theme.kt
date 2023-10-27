package com.example.exercideonereggistration.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController





private val DarkColorPalette = darkColorScheme(
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.text,
    surface = ThemeColors.Night.surafce,
    background = ThemeColors.Night.bacground,
    onBackground = ThemeColors.Night.onBacground,
    secondaryContainer = ThemeColors.Night.button
)

private val LightColorPalette = lightColorScheme(
    primary = ThemeColors.Day.primary,
    onPrimary = ThemeColors.Day.text,
    surface = ThemeColors.Day.surafce,
    background = ThemeColors.Day.bacground,
    onBackground = ThemeColors.Day.onBacground,
    secondaryContainer = ThemeColors.Day.button
)
/*
@Composable
fun AppThemed(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorPalette = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val statusBarColor = if (darkTheme) {
        colorPalette.background
    } else {
        Color.White
    }

    SetStatusBarColor(statusBarColor)

    val backgroundColor = if (darkTheme) {
        Color.Black // Set to black in dark mode
    } else {
        Color.White // Set to white in light mode
    }

    MaterialTheme(
        colorScheme = colorPalette,
        typography = Typography
    )*/

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}
@Composable
fun AppThemed(content: @Composable () -> Unit) {
    val isDarkMode = isSystemInDarkTheme()
    val statusBarColor = if (isDarkMode) {
        Color.DarkGray // Customize the status bar color for dark mode
    } else {
        Color.Blue // Customize the status bar color for light mode
    }
    val backgroundColor = if (isDarkMode) {
         Color(0xFF121212) // Dark background color
    } else {
        Color.White // Light background color
    }

    val colors =  if (isDarkMode) {
       DarkColorPalette
    } else {
       LightColorPalette
    }


    val systemUiController = rememberSystemUiController()
    if(isDarkMode){
        systemUiController.setSystemBarsColor(
            color = Color(0xFF121212)
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }



    MaterialTheme(
        colorScheme = colors

    ) {
        // Set the background color for the entire app
        Surface(
            color = backgroundColor,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}
