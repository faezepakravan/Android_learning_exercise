package com.example.exercideonereggistration.UIFile

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.exercideonereggistration.dataStorage.StoreData
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.exercideonereggistration.ui.theme.AppThemed
import com.example.exercideonereggistration.ui.theme.LocalAppColor

class LoginActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemed {
                val appColor = LocalAppColor.current
                SetStatusBarColor(color = appColor.mainBackground)

                LogingUi()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LogingUi() {
    var textPassword by remember { mutableStateOf(TextFieldValue("")) }
    var number by remember { mutableStateOf(TextFieldValue()) }
    var maxChar = 12

    var openAlertDialog = remember { mutableStateOf(false) }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 350.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        TextField(
            value = number,
            label = { Text(text = "Input Your phone number") },
            onValueChange = { it -> number = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        TextField(
            value = textPassword,
            label = { Text(text = "Input Your Password") },
            singleLine = true,
            onValueChange = {
                if (it.text.length <= maxChar) textPassword = it
            }
        )



        Button(

            onClick = {
                if (acceptedPassAndNumber(textPassword.text, number.text)) {


                      //  saveData.saveData(number.text, textPassword.text)



                } else {
                    openAlertDialog.value = true
                }
            }, shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .width(200.dp)
                .padding(top = 30.dp), colors = ButtonDefaults.buttonColors(Color.DarkGray)
        ) {
            Text(
                text = "submit",
                color = Color.White
            )
        }


        if (openAlertDialog.value) {

            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                text = when {

                    !numberPatternAccepted(number.text) && passAccepted(textPassword.text) -> {
                        "Number is not accepted"
                    }

                    !passAccepted(textPassword.text) && numberPatternAccepted(number.text) -> {
                        "Password is not acceptable"

                    }


                    else -> "Please enter correctly"
                }
            )
        }
    }
}


fun acceptedPassAndNumber(pass: String, number: String): Boolean {
    var passAccepted = passAccepted((pass))
    var numberPatternAccepted = numberPatternAccepted(number)

    return if (passAccepted && numberPatternAccepted) return true
    else false
}

fun numberPatternAccepted(number: String): Boolean {
    return number.length == 11 && number.startsWith("09")


}

fun passAccepted(pass: String): Boolean {
    var pattern = "... A sophisticated Regex Pattern ... ".toRegex()
    return if (pass.length > 8 && !pass.matches(pattern)) return true
    else false
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    text: String
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .height(100.dp)
                .width(300.dp)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onDismissRequest() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("OK")
            }
        }
    }
}

