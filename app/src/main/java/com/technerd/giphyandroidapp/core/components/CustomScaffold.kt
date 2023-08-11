package com.technerd.giphyandroidapp.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomScaffold(
    modifier: Modifier? = Modifier,
    snackbarHost: (@Composable () -> Unit)? = {},
    topBar: (@Composable () -> Unit)? = {},
    childComposable: @Composable () -> Unit,
    bottomBar: (@Composable () -> Unit)? = {},
    floatingActionButton: (@Composable () -> Unit)? = {},
    desiredPadding: PaddingValues? = null,
    desiredFabPosition: FabPosition? = null,
) {
    Scaffold(
        snackbarHost = snackbarHost!!,
        topBar = topBar!!,
        modifier = modifier ?: Modifier.fillMaxSize(),
        bottomBar = bottomBar ?: {},
        floatingActionButton = floatingActionButton ?: {},
        floatingActionButtonPosition = desiredFabPosition ?: FabPosition.Center,
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(desiredPadding ?: paddingValues)
        ) {
            childComposable()
        }
    }
}