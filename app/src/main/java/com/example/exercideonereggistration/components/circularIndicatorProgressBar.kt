package com.example.exercideonereggistration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun circularIndicatorProgressBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(300.dp) // Adjust the size as needed
                .background(MaterialTheme.colorScheme.background, CircleShape),
            color = MaterialTheme.colorScheme.secondaryContainer,
            strokeWidth = 6.dp
        )

        // Spacer to center the text vertically
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Loading!",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 25.sp,
        )
    }
}
