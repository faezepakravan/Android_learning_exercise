package com.example.exercideonereggistration.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
import com.example.exercideonereggistration.ui.theme.ThemeColors.Day.button
import com.example.exercideonereggistration.ui.theme.ThemeColors.Day.text
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.time.format.TextStyle


private val DarkColorPalette = darkColorScheme(
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.onPrimary,
    surface = ThemeColors.Night.surafce,
    background = ThemeColors.Night.bacground,
    onBackground = ThemeColors.Night.onBacground,
    secondaryContainer = ThemeColors.Night.button
)

private val LightColorPalette = lightColorScheme(
    primary = ThemeColors.Day.primary,
    onPrimary = ThemeColors.Day.onPrimary,
    surface = ThemeColors.Day.surafce,
    background = ThemeColors.Day.bacground,
    onBackground = ThemeColors.Day.onBacground,
    secondaryContainer = ThemeColors.Day.button
)


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

    val colors = if (isDarkMode) {
        DarkColorPalette
    } else {
        LightColorPalette
    }


    val systemUiController = rememberSystemUiController()
    if (isDarkMode) {
        systemUiController.setSystemBarsColor(
            color = Color(0xFF121212)
        )
    } else {
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


@Composable
fun CustomButton(
    onClickAction: () -> Unit,
    //content: @Composable () -> Unit,
    buttonText: String
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClickAction)
            .width(180.dp)
            .height(40.dp)
            .fillMaxHeight(.001f)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(35.dp) // Default shape applied here
            ),
        contentAlignment = Alignment.Center,
        content = {
            Text(text = buttonText, color = Color.White, fontSize = 16.sp)
        }
    )

}
