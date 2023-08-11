package com.technerd.giphyandroidapp.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technerd.giphyandroidapp.features.dashboard.presentation.screens.DashboardScreen
import com.technerd.giphyandroidapp.features.favgifs.presentation.screens.FavouriteGifsScreen
import com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens.TrendingGIFsScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Dashboard.route) {
        composable(route = Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(route = Screen.TrendingGifs.route) {
            TrendingGIFsScreen()
        }
        composable(route = Screen.FavouriteGifs.route) {
            FavouriteGifsScreen()
        }
    }
}