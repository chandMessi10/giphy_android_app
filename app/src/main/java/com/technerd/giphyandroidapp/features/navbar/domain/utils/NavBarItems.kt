package com.technerd.giphyandroidapp.features.navbar.domain.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import com.technerd.giphyandroidapp.features.navbar.domain.model.NavBarItemModel

object NavBarItems {
    val BarItems = listOf(
        NavBarItemModel(
            title = "Trending",
            image = Icons.Filled.LocalFireDepartment,
            route = "trending_gifs_screen"
        ),
        NavBarItemModel(
            title = "Favourite",
            image = Icons.Filled.Favorite,
            route = "favourite_gifs_screen"
        ),
    )
}