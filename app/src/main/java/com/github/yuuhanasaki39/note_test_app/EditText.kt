package com.github.yuuhanasaki39.note_test_app

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(viewModel: EditTextViewModel) {
    val inputState by viewModel.textState.collectAsState("")

    var textEnabled by remember { mutableStateOf(true) }

    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            RealityTextField(
                inputText = inputState,
                focusRequester = focusRequester,
                onValueChanged = {
                    viewModel.updateText(inputState)
                },
                onClickSend = {
                    viewModel.updateText(inputState)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RealityTextField(
    inputText: String,
    focusRequester: FocusRequester,
    onValueChanged: (String) -> Unit,
    onClickSend: () -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(inputText, TextRange(inputText.length))
        )
    }
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChanged(it.text)
        },
        placeholder = { Text("Enter Text") },
        trailingIcon = {
            TextFieldIcon(
                isTextEmpty = textFieldValue.text.isEmpty(),
                onClickSend = onClickSend
            )
        },
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
    )
}

@Composable
private fun TextFieldIcon(isTextEmpty: Boolean, onClickSend: () -> Unit) {
    if (isTextEmpty) {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = ""
            )
        }
    } else {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onClickSend,
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = ""
            )
        }
    }

}