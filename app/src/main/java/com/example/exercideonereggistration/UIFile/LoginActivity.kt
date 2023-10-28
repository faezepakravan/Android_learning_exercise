package com.example.exercideonereggistration.UIFile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.exercideonereggistration.dataStorage.StoreData
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exercideonereggistration.components.alertDialog
import kotlinx.coroutines.launch
import com.example.exercideonereggistration.ui.theme.AppThemed
import com.example.exercideonereggistration.ui.theme.CustomButton
import kotlinx.coroutines.delay


class LoginActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemed() {
                LogingUi(context = this)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun LogingUi(context: Activity) {

    var textPassword by remember { mutableStateOf(TextFieldValue("")) }
    var number by remember { mutableStateOf(TextFieldValue()) }
    var maxChar = 12
    var openAlertDialog = remember { mutableStateOf(false) }
    val dataStored = StoreData(context)
    val coroutinScope = rememberCoroutineScope()
    var openLoadingAlertDialog = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var passwordVisible by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    DisposableEffect(Unit) {
        val activity = context as ComponentActivity
        val decorView = activity.window.decorView

        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(decorView)
            if (insets != null) {
                val isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
                if (!isKeyboardVisible) {
                    keyboardController?.hide()
                }
            }
        }

        decorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)

        onDispose {
            decorView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
        }
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = scrollState)
            .focusRequester(focusRequester),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        OutlinedTextField(value = number,
            label = { Text(text = "Input Your number") },
            singleLine = true,
            onValueChange = {
                val cleanedText = it.text.replace(" ", "")
                if (cleanedText.length <= 11) {
                    number = TextFieldValue(text = cleanedText, selection = it.selection)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,

                ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .padding(3.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (!it.isFocused) {
                        keyboardController?.hide()
                    }
                },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusRequester.requestFocus()
                    focusManager.clearFocus()
                }
            )
        )


        OutlinedTextField(value = textPassword,
            label = { Text(text = "Input Your Password") },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            onValueChange = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(Int.MAX_VALUE)
                }
                val cleanedText = it.text.replace(" ", "")
                if (it.text.length <= maxChar) {
                    textPassword =
                        TextFieldValue(text = cleanedText, selection = it.selection)
                }
                coroutinScope.launch { scrollState.animateScrollTo(scrollState.maxValue) }

            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .padding(16.dp)
                .focusRequester(focusRequester),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }),
            visualTransformation =
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (passwordVisible) {
                    IconButton(onClick = { passwordVisible = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { passwordVisible = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )


        Button(

            onClick = {

                focusRequester.requestFocus()
                keyboardController?.hide()
                if (acceptedPassAndNumber(textPassword.text, number.text)) {
                    coroutinScope.launch {
                        dataStored.saveData(StoreData.numberKey, textPassword.text)
                    }
                    openLoadingAlertDialog.value = true
                    coroutinScope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                        delay(3000)
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        context.finish()
                    }


                } else {
                    openAlertDialog.value = true
                }
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .width(200.dp)
                .padding(top = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = "submit", color = Color.White
            )
        }

    }


    if (openLoadingAlertDialog.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            alertDialog()
        }
    }
    if (openAlertDialog.value) {

        AlertDialogExample(
            onDismissRequest = { openAlertDialog.value = false }, text = when {

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
    var containsNumber = false
    var containsUppercase = false
    for (i in pass) {
       if (i.isDigit())containsNumber = true
        if (i.isUpperCase()) containsUppercase = true
    }
    return if (pass.length in 9..14 && !pass.matches(pattern) && containsNumber && containsUppercase) return true
    else false
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit, text: String
) {

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp),

            shape = RoundedCornerShape(25.dp),
        ) {


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text, /*color = MaterialTheme.colorScheme.onPrimary*/
                )
                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(onClickAction = {onDismissRequest()}, buttonText = "ok")


            }
        }
    }
}





