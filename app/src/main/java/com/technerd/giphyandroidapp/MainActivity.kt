package com.technerd.giphyandroidapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.technerd.giphyandroidapp.core.components.CustomScaffold
import com.technerd.giphyandroidapp.core.navigation.NavGraph
import com.technerd.giphyandroidapp.ui.theme.GiphyAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyAndroidAppTheme {
                navController = rememberNavController()
                CustomScaffold(
                    childComposable = {
                        NavGraph(navHostController = navController)
                    },
                )
            }
        }
    }
}