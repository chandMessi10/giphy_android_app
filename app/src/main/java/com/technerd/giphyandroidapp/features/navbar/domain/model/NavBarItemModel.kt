package com.technerd.giphyandroidapp.features.navbar.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItemModel(
    val title: String,
    val image: ImageVector,
    val route: String,
)
