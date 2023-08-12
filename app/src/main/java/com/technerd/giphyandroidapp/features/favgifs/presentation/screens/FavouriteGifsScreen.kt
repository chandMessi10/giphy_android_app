package com.technerd.giphyandroidapp.features.favgifs.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.technerd.giphyandroidapp.core.components.CustomErrorComponent
import com.technerd.giphyandroidapp.core.components.CustomProgressIndicator
import com.technerd.giphyandroidapp.core.components.ImageExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteGifsScreen(viewModel: FavouriteGIFViewModel = hiltViewModel()) {

    val allDbProducts by viewModel.allFavouriteGIFs.observeAsState(listOf())
    val loadingState by viewModel.loadingState.observeAsState()
    val successState by viewModel.successState.observeAsState()
    val failureState by viewModel.failureState.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(left = 8.dp, right = 8.dp),
    ) {
        when {
            loadingState == true -> CustomProgressIndicator()

            successState == true -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    content = {
                        items(
                            count = allDbProducts.size,
                            itemContent = { favouriteGIF ->
                                Card(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .fillMaxWidth(),
                                    onClick = { },
                                ) {
                                    ImageExample(
                                        gifHeight = 200,
//                                        gifUrl = favouriteGIF.gifUrl,
                                        favFunction = {},
                                        gifUrl = "https://media1.giphy.com/media/REEp9iIP70X5BKlnql/200.gif?cid=f6d5e040oyrq1al2wspq64nd4zn3yixw8x38diq6y0xwt8gm&ep=v1_gifs_trending&rid=200.gif&ct=g",
                                    )
                                }
                            },
                        )
                    },
                )
            }

            failureState == true -> {
                CustomErrorComponent()
                Toast.makeText(
                    LocalContext.current, "Error fetching trending gifs", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}