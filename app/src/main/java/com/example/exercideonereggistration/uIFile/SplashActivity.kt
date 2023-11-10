package com.example.exercideonereggistration.uIFile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercideonereggistration.R
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
            contentAlignment = Alignment.Center
        ) {

            LaunchedEffect(Unit) {
                delay(4000)
                loading.value = false
            }
            if (loading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.border(3.dp, Color.Green))
                        circularIndicatorProgressBar()
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = stringResource(R.string.Loading),
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
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

