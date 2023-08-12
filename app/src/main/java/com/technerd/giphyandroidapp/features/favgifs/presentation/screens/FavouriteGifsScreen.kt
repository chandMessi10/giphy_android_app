package com.technerd.giphyandroidapp.features.favgifs.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.technerd.giphyandroidapp.R
import com.technerd.giphyandroidapp.core.components.CustomErrorComponent
import com.technerd.giphyandroidapp.core.components.CustomProgressIndicator
import com.technerd.giphyandroidapp.core.components.ImageExample

@Composable
fun FavouriteGifsScreen(viewModel: FavouriteGIFViewModel = hiltViewModel()) {

    val allDbProducts by viewModel.allFavouriteGIFs.observeAsState(listOf())
    val isLoadingFavouriteGIFs by viewModel.isLoading
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoadingFavouriteGIFs) {
            CustomProgressIndicator()
        } else if (allDbProducts.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(
                        count = allDbProducts.size,
                        itemContent = {
                            Box(modifier = Modifier.padding(8.dp)) {
                                ImageExample(
                                    gifHeight = 200,
                                    favFunction = {
                                        viewModel.deleteFavouriteGIF(allDbProducts[it].gifIDValue)
                                        Toast.makeText(
                                            context, "GIF removed successfully", Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    gifUrl = allDbProducts[it].gifURL,
                                    isFav = true,
                                )
                            }
                        },
                    )
                },
            )
        } else if (allDbProducts.isEmpty()) {
            CustomErrorComponent(R.raw.empty_fav_gifs)
        } else {
            CustomErrorComponent()
            Toast.makeText(
                LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT
            ).show()
        }
    }
}