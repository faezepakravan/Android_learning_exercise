package com.example.exercideonereggistration.components


import androidx.compose.runtime.Composable
import com.example.exercideonereggistration.components.DialogType.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.remember
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.exercideonereggistration.R
import kotlinx.coroutines.delay


@Composable
fun AppDialog(

    dialogType: DialogType,
    message: String?,
    onDismiss: (() -> Unit)?
) {

    Dialog(
        onDismissRequest = { onDismiss?.invoke() },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(.3f),
            MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Box(contentAlignment = Alignment.Center) {
                when (dialogType) {
                    MessageDialog -> {
                        if (message != null) {
                            onDismiss?.let { onDismissFunction ->
                                MessageDialogContent(onDismissFunction, message)
                            }
                        }
                    }

                    LoadingDialog -> LoadingDialogContent()
                }
            }
        }
    }
}


enum class DialogType {
    LoadingDialog,
    MessageDialog
}

@Composable
fun LoadingDialogContent() {
    val loading = remember {
        mutableStateOf(true)
    }
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
            Box(/*modifier = Modifier.fillMaxSize(.5f*/) {
                Box(modifier = Modifier.wrapContentSize()) {
                    Column(
                        modifier = Modifier,
                        Arrangement.Center
                    ) {
                        circularIndicatorProgressBar()
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(id = R.string.Loading),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }


                }

            }
        }
    }
}

@Composable
fun MessageDialogContent(onDismissRequest: () -> Unit, text: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text, color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            onClickAction = onDismissRequest,
            buttonText = stringResource(id = R.string.okBtnText),
            modifier = Modifier
        )

    }
}

