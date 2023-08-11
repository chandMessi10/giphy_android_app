package com.technerd.giphyandroidapp.core.components

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun ImageExample(gifHeight: Int, gifUrl: String) {
    val imageLoader = ImageLoader.Builder(LocalContext.current).components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()

    Box(
        modifier = Modifier
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .height(gifHeight.dp)
                .fillMaxWidth(),
            model = ImageRequest.Builder(LocalContext.current).data(data = gifUrl).build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            loading = {
                CustomProgressIndicator()
            },
            error = {
                CustomGIFErrorComponent()
            },
            imageLoader = imageLoader
        )
        Card(
            modifier = Modifier.padding(8.dp).align(alignment = Alignment.TopEnd)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
            }
        }
    }
}