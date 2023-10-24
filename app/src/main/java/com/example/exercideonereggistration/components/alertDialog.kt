package com.example.exercideonereggistration.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import kotlinx.coroutines.delay

@Composable
fun alertDialog() {
    MaterialTheme {
        Card(

            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer),
            shape = RoundedCornerShape(50.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(.8f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val loading = remember {
                        mutableStateOf(true)
                    }

                    LaunchedEffect(Unit) {
                        delay(4000)
                        loading.value = false
                    }
                    if (loading.value) {
                        circularIndicatorProgressBarINDialog()
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Loading!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}


@Composable
fun circularIndicatorProgressBarINDialog(
) {

    CircularProgressIndicator(
        modifier = Modifier.fillMaxSize(.5f),
        color = MaterialTheme.colorScheme.onSecondary,
        strokeWidth = 6.dp
    )
}