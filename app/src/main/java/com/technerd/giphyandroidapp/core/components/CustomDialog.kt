package com.technerd.giphyandroidapp.core.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomAlertDialog(
    dismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    title: String,
    supportingText: String,
) {
    AlertDialog(
        onDismissRequest = dismissRequest,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        title = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
        text = {
            Text(
                text = supportingText, style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        },
        shape = RoundedCornerShape(8.dp),
    )
}