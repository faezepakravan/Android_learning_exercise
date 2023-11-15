package com.example.exercideonereggistration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercideonereggistration.ui.theme.space


@Composable
fun CustomButton(
    onClickAction: () -> Unit,
    buttonText: String, modifier: Modifier
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = modifier
            .width(screenWidth * 2 / 5)
            .height(screenHeight * 1 / 25)
            .clickable(onClick = onClickAction)
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = MaterialTheme.shapes.large // Default shape applied here
            ),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = buttonText,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    )

}
