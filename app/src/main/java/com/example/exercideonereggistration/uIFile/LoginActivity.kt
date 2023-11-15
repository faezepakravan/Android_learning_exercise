package com.example.exercideonereggistration.uIFile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exercideonereggistration.R
import com.example.exercideonereggistration.components.AppDialog
import com.example.exercideonereggistration.components.CustomButton
import com.example.exercideonereggistration.components.DialogType
import com.example.exercideonereggistration.dataStorage.StoreData
import com.example.exercideonereggistration.dataStorage.dataStore
import com.example.exercideonereggistration.ui.theme.AppThemed
import com.example.exercideonereggistration.ui.theme.space
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemed {
                LoginUI(context = this)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun LoginUI(context: Activity) {

    var textPassword by remember { mutableStateOf(TextFieldValue("")) }
    var textNumber by remember { mutableStateOf(TextFieldValue()) }
    val maxChar = 12
    val dataStored = StoreData(context.dataStore)
    val corotineScope = rememberCoroutineScope()
    val showErrorDialog = remember { mutableStateOf(false) }
    val showLoadingDialog = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focueNameRequest = remember { FocusRequester() }
    val focuePassRequest = remember { FocusRequester() }

    var passwordVisible by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val numberError by remember { mutableStateOf(false) }
    val passError by remember { mutableStateOf(false) }


    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var okPass: Boolean
    var okNumber: Boolean

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
            .padding(MaterialTheme.space.medium)
            .verticalScroll(state = scrollState)
            .focusRequester(focuePassRequest),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        okPass = passAccepted(textPassword.text)
        okNumber = numberPatternAccepted(textNumber.text)


        OutlinedTextField(value = textNumber,
            label = { Text(text = stringResource(id = R.string.passInput)) },
            singleLine = true,
            onValueChange = {

                val cleanedText = it.text.replace(" ", "")
                if (cleanedText.length <= 11) {
                    textNumber = TextFieldValue(text = cleanedText, selection = it.selection)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,

                ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .padding(3.dp)
                .focusRequester(focueNameRequest)
                .onFocusChanged {
                    if (!it.isFocused) {
                        keyboardController?.hide()
                    }
                },
            keyboardActions = KeyboardActions(
                onDone = {
                    focueNameRequest.requestFocus()
                    focusManager.clearFocus()
                }
            )
        )


        OutlinedTextField(
            value = textPassword,
            label = { Text(text = stringResource(id = R.string.passInput)) },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            onValueChange = {
                val cleanedText = it.text.replace(" ", "")
                if (it.text.length <= maxChar) {
                    textPassword =
                        TextFieldValue(text = cleanedText, selection = it.selection)
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .padding(16.dp)
                .focusRequester(focuePassRequest),
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
        Spacer(modifier = Modifier.height(screenHeight * 1 / 30))
        CustomButton(
            onClickAction = {
                focuePassRequest.requestFocus()
                keyboardController?.hide()
                if (okPass && okNumber) {
                    corotineScope.launch {
                        dataStored.saveData(StoreData.numberKey, textPassword.text)
                    }
                    showLoadingDialog.value = true
                    corotineScope.launch {
                        delay(3000)
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        context.finish()
                    }

                } else {
                    showErrorDialog.value = true
                }
            },
            buttonText = stringResource(id = R.string.submitButton),
            modifier = Modifier
        )
    }

    if (showLoadingDialog.value) {
        AppDialog(DialogType.LoadingDialog, null, null)
    }

    if (showErrorDialog.value) {

        AppDialog(
            dialogType = DialogType.MessageDialog,
            message = checkPasswordResultMessage(textNumber.text, textPassword.text, context),
            onDismiss = {
                showErrorDialog.value = false
                if (!numberPatternAccepted(textNumber.text)) {
                    focueNameRequest.requestFocus()
                    textNumber = TextFieldValue()
                }
                if (!passAccepted(textPassword.text)) {
                    focuePassRequest.requestFocus()
                    textPassword = TextFieldValue()
                }
            }
        )
    }
}


fun checkPasswordResultMessage(number: String, password: String, context: Context): String {
    return when {
        !numberPatternAccepted(number) -> context.getString(R.string.numberIsNotAccepted)
        !passAccepted(password) -> passwordErrorMessage(password, context)
        else -> ""
    }

}


fun numberPatternAccepted(number: String): Boolean {
    return number.length == 11 && number.startsWith("09")
}


fun passwordErrorMessage(password: String, context: Context): String {
    val pattern = "... A sophisticated Regex Pattern ... ".toRegex()
    var containsNumber = false
    var containsUppercase = false
    var containLowercase = false
    for (i in password) {
        if (i.isDigit()) containsNumber = true
        if (i.isUpperCase()) containsUppercase = true
        if (i.isLowerCase()) containLowercase = true
    }

    return when {
        password.length < 8 -> context.getString(R.string.numberInPass)
        !containsNumber -> context.getString(R.string.numberInPass)
        !containsUppercase -> context.getString(R.string.upperAlphabet)
        !containLowercase -> context.getString(R.string.lowerAlphabet)
        else -> {
            "your password is not accepted. /n try again!"
        }
    }
}

fun passAccepted(pass: String): Boolean {
    val pattern = "... A sophisticated Regex Pattern ... ".toRegex()
    var containsNumber = false
    var containsUppercase = false
    var containLowercase = false
    for (i in pass) {
        if (i.isDigit()) containsNumber = true
        if (i.isUpperCase()) containsUppercase = true
        if (i.isLowerCase()) containLowercase = true
    }
    return if (pass.length in 8..14 && !pass.matches(pattern) && containsNumber && containsUppercase
        && containLowercase
    ) return true
    else false
}





