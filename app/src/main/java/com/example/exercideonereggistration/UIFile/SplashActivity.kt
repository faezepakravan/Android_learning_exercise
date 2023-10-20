package com.example.exercideonereggistration.UIFile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.exercideonereggistration.components.circularIndicatorProgressBar
import com.example.exercideonereggistration.ui.theme.AppThemed
import com.example.exercideonereggistration.ui.theme.LocalAppColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemed {
                val appColor = LocalAppColor.current
                SetStatusBarColor(color = appColor.mainBackground)

                splashUi()
            }
        }
    }


    @Composable
    fun splashUi() {
        val appColor = LocalAppColor.current
        val loading = remember {
            mutableStateOf(true)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(appColor.mainBackground),
            contentAlignment = Alignment.Center
        ) {
            LaunchedEffect(Unit) {
                delay(4000)
                loading.value = false
            }
           if (loading.value) {circularIndicatorProgressBar()}
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}