package com.technerd.giphyandroidapp.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier? = Modifier,
    titleLabel: String? = "",
    actions: @Composable (RowScope.() -> Unit)? = null,
    navigationIconOnClickAction: (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    navigationIconDescription: String? = null,
    enableTopBarBackgroundColor: Boolean? = true,
) {
    TopAppBar(
        title = {
            if (titleLabel != null) {
                Text(
                    text = titleLabel, style = TextStyle(
                        fontSize = 20.sp,
                    )
                )
            }
        },
        modifier = modifier ?: Modifier,
        navigationIcon = {
            if (navigationIconOnClickAction != null && navigationIcon != null) {
                IconButton(onClick = navigationIconOnClickAction) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconDescription,
                    )
                }
            }
            if (navigationIconOnClickAction != null && navigationIcon == null) {
                IconButton(onClick = navigationIconOnClickAction) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = navigationIconDescription,
                    )
                }
            }
        },
        actions = actions ?: {},
        colors = if (enableTopBarBackgroundColor != null && enableTopBarBackgroundColor) TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) else TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}