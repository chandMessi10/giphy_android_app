package com.technerd.giphyandroidapp.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun NoiceTextField(
    modifier: Modifier? = Modifier,
    textFieldValue: String,
    textFieldLabel: String,
    onValueChangeMethod: (String) -> Unit,
    showPasswordIcon: Boolean = false,
    isSingleLine: Boolean = false,
    desiredKeyboardActions: KeyboardActions,
    desiredImeAction: ImeAction? = null,
    desiredKeyboardType: KeyboardType? = null,
    desiredSupportText: String? = null,
    formFieldError: Boolean = false,
) {
    val showPassword = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            onValueChangeMethod(it)
        },
        shape = RoundedCornerShape(8.dp),
        label = { Text(text = textFieldLabel) },
        modifier = modifier?.fillMaxWidth() ?: Modifier
            .fillMaxWidth(),
        singleLine = isSingleLine,
        trailingIcon = {
            if (showPasswordIcon) {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        imageVector = if (showPassword.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Visibility"
                    )
                }
            }
        },
        visualTransformation = if (showPasswordIcon) (if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()) else VisualTransformation.None,
        keyboardActions = desiredKeyboardActions,
        keyboardOptions = KeyboardOptions(
            imeAction = desiredImeAction ?: ImeAction.Done,
            keyboardType = desiredKeyboardType ?: KeyboardType.Text,
        ),
        isError = formFieldError,
        supportingText = {
            if (desiredSupportText != null) {
                Text(text = desiredSupportText)
            }
        },
    )
}