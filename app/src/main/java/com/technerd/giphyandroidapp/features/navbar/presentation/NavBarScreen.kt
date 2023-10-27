package com.technerd.giphyandroidapp.features.navbar.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.technerd.giphyandroidapp.core.components.CustomScaffold
import com.technerd.giphyandroidapp.features.navbar.presentation.components.BottomNavBar
import com.technerd.giphyandroidapp.features.navbar.presentation.components.NavBarNavigationHost

@Composable
fun NavBarScreen() {
    val navbarNavController = rememberNavController()

    CustomScaffold(
        childComposable = {
            NavBarNavigationHost(
                navbarNavController = navbarNavController
            )
        },
        bottomBar = {
            BottomNavBar(navbarNavController)
        }
    )
}