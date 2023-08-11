package com.technerd.giphyandroidapp.features.favgifs.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.technerd.giphyandroidapp.core.components.ImageExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteGifsScreen() {
    val screenScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(left = 8.dp, right = 8.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                items(
                    count = 30,
                    itemContent = {
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            onClick = { },
                        ) {
                            ImageExample(
                                gifHeight = 200,
                                gifUrl = "https://media1.giphy.com/media/REEp9iIP70X5BKlnql/200.gif?cid=f6d5e040oyrq1al2wspq64nd4zn3yixw8x38diq6y0xwt8gm&ep=v1_gifs_trending&rid=200.gif&ct=g"
                            )
                        }
                    },
                )
            },
        )
    }
}