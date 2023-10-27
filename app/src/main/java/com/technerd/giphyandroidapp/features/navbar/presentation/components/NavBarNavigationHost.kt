package com.technerd.giphyandroidapp.features.navbar.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technerd.giphyandroidapp.core.navigation.Screen
import com.technerd.giphyandroidapp.features.favgifs.presentation.screens.FavouriteGifsScreen
import com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens.TrendingGIFsScreen

@Composable
fun NavBarNavigationHost(navbarNavController: NavHostController) {
    NavHost(navController = navbarNavController, startDestination = Screen.TrendingGifs.route) {
        composable(route = Screen.TrendingGifs.route) {
            TrendingGIFsScreen()
        }
        composable(route = Screen.FavouriteGifs.route) {
            FavouriteGifsScreen()
        }
    }
}