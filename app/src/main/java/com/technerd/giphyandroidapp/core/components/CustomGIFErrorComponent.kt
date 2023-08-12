package com.technerd.giphyandroidapp.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomGIFErrorComponent() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = Icons.Filled.Error, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Error loading gif 😢",
                style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
            )
        }
    }
}