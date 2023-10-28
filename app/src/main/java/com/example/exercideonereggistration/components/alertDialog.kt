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
        val loading = remember {
            mutableStateOf(true)
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(50.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight(.35f)
                    .fillMaxWidth(.55f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LaunchedEffect(Unit) {
                        delay(3000)
                        loading.value = false
                    }
                    if (loading.value) {
                        circularIndicatorProgressBarINDialog()
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Loading!",
                        color = MaterialTheme.colorScheme.onPrimary
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
        color = MaterialTheme.colorScheme.onPrimary,
        strokeWidth = 6.dp
    )
}