package com.example.exercideonereggistration.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exercideonereggistration.ui.theme.LocalAppColor

@Composable
fun circularIndicatorProgressBar(
) {
    val appColor = LocalAppColor.current

        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(.6f),

            color = appColor.smallText,
            // trackColor = Color.Magenta.copy(alpha = 0.4f),
            strokeWidth = 6.dp
        )
    }
