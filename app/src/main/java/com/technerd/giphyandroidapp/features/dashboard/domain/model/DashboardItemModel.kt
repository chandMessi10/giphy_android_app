package com.technerd.giphyandroidapp.features.dashboard.domain.model

import androidx.compose.runtime.Composable

data class DashboardItemModel(
    val title: String,
    val screen: @Composable () -> Unit,
)
