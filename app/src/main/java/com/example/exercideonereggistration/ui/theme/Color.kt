package com.example.exercideonereggistration.ui.theme

import androidx.compose.ui.graphics.Color




    val mainButton = Color(0xFF451952)

    val editTextButtonDark = Color(0xFFEEEEEE)
    val editTextButtonLight = Color(0xFFFFE5E5)


    val btnDesignInDialog = Color(0xFfF39F5A)

   val  dialogBackgroundDark = Color(0xFFF2F6F5)
   val  dialogBackgroundLight = Color(0xFF7D7C7C)

    val primaryText = Color.White
    val titleBold = Color.White

    val smallTextDark = Color(0xFF662549)
    val smallTextLight = Color(0xFF363062)

   val  mainBackgroundDark = Color(0xFF1F1D36)
   val  mainBackgroundLight = Color(0xFFFBF0F0)

   val  additionalBackgroundLight = Color(0xFfD8B9C3)
   val  additionalBackgroundDark = Color(0xFf4D4C7D)






// define your colors for dark theme
val clear_dark = Color(0xFfF39F5A)
val dark_btn = Color(0xFF222427)

// define your colors for dark theme
val light_btn = Color(android.graphics.Color.parseColor("#E9F0F4"))
val light_bg = Color(android.graphics.Color.parseColor("#F6F8F9"))
val clear_light = Color(0xFFF1C8D1)


sealed class ThemeColors(
    val bacground: Color,
    val onBacground : Color,
    val surafce: Color,
    val primary: Color,
    val onPrimary : Color,
    val text: Color,
    val button :Color
) {
    object Night : ThemeColors(
        bacground = dark_btn,
        onBacground =dark_btn,
        surafce = dialogBackgroundDark,
        primary = mainBackgroundLight,
        onPrimary = mainBackgroundDark,
        text = Color.White,
        button = clear_dark
    )

    object Day : ThemeColors(
        bacground = light_bg,
        onBacground = dark_btn,
        surafce = additionalBackgroundDark,
        primary = mainBackgroundDark,
        onPrimary = mainBackgroundLight ,
        text = Color.Black,
        button = clear_dark
    )
}