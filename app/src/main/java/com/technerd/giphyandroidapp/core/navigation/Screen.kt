package com.technerd.giphyandroidapp.core.navigation

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard_screen")
    object TrendingGifs : Screen("trending_gifs_screen")
    object FavouriteGifs : Screen("favourite_gifs_screen")
}
