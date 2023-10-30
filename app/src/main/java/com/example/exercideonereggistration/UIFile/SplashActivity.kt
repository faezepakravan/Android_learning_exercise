package com.example.exercideonereggistration.UIFile

import android.content.Context
import android.content.Intent
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

import androidx.compose.ui.platform.LocalContext
import com.example.exercideonereggistration.components.circularIndicatorProgressBar
import com.example.exercideonereggistration.dataStorage.StoreData
import com.example.exercideonereggistration.dataStorage.dataStore
import com.example.exercideonereggistration.ui.theme.AppThemed
import kotlinx.coroutines.delay


class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemed {
                splashUi(LocalContext.current, true)
            }
        }
    }


    @Composable
    fun splashUi(context: Context, valid: Boolean) {

            val loading = remember {
                mutableStateOf(true)
            }
            val storeData = StoreData(context.dataStore)

            Box(
                modifier = Modifier

                    .fillMaxSize(),
                   // .background(AppTheme.colors.mainBackground),
                contentAlignment = Alignment.Center
            ) {
                LaunchedEffect(Unit) {
                    delay(4000)
                    loading.value = false
                }
                if (loading.value) {
                    circularIndicatorProgressBar()
                }

                LaunchedEffect(key1 = valid) {
                    delay(4000)
                    val number = storeData.getData(StoreData.numberKey)
                    if (number != null) {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)

                    } else {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)

                    }
                }

            }
        }
    }

