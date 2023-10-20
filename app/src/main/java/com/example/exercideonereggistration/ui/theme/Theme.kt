package com.example.exercideonereggistration.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Immutable
data class AppColor(
    val mainButton: Color,
    val editTextButton: Color,
    val btnDesignInDialog: Color,
    val dialogBackground: Color,
    val primaryText: Color,
    val titleBold: Color,
    val smallText: Color,
    val mainBackground: Color,
    val additionalBackground: Color,
)

val LocalAppColor = staticCompositionLocalOf {
    AppColor(
        mainButton = Color.Unspecified,
        editTextButton = Color.Unspecified,
        btnDesignInDialog = Color.Unspecified,
        dialogBackground = Color.Unspecified,
        primaryText = Color.Unspecified,
        titleBold = Color.Unspecified,
        smallText = Color.Unspecified,
        mainBackground = Color.Unspecified,
        additionalBackground = Color.Unspecified
    )
}

val DarkTheme = AppColor(

    mainButton = Color(0xFF451952),
    editTextButton = Color(0xFFEEEEEE),
    btnDesignInDialog = Color(0xFFfF39F5A),
    dialogBackground = Color(0xFFF2F6F5),
    primaryText = Color.White,
    titleBold = Color.White,
    smallText = Color(0xFF662549),
    mainBackground = Color(0xFF1F1D36),
    additionalBackground = Color(0xFF4D4C7D)


)

val LightTheme = AppColor(

    mainButton = Color(0xFF451952),
    editTextButton = Color(0xFFFFE5E5),
    btnDesignInDialog = Color(0xFFfF39F5A),
    dialogBackground = Color(0xFF7D7C7C),
    primaryText = Color.White,
    titleBold = Color.White,
    smallText = Color(0xFF363062),
    mainBackground = Color(0xFFFBF0F0),
    additionalBackground = Color(0xFFD8B9C3),
)


@Composable
fun AppThemed(
    content: @Composable () -> Unit
) {
    val color = if (isSystemInDarkTheme()) {
        DarkTheme
    } else {
        LightTheme
    }

    CompositionLocalProvider(
        LocalAppColor provides color,

        ) {
        MaterialTheme(
            content = content,

            )
    }
}

