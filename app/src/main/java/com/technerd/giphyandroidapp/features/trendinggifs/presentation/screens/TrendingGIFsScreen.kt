package com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.technerd.giphyandroidapp.core.components.CustomErrorComponent
import com.technerd.giphyandroidapp.core.components.CustomProgressIndicator
import com.technerd.giphyandroidapp.core.components.CustomScaffold
import com.technerd.giphyandroidapp.core.components.CustomTopBar
import com.technerd.giphyandroidapp.core.components.ImageExample
import com.technerd.giphyandroidapp.core.components.NoiceTextField
import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.features.favgifs.domain.model.FavouriteGIF
import com.technerd.giphyandroidapp.features.favgifs.presentation.screens.FavouriteGIFViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrendingGIFsScreen(
    viewModel: TrendingGIFsViewModel = hiltViewModel(),
    favViewModel: FavouriteGIFViewModel = hiltViewModel(),
) {
    val searchValue by viewModel.searchInputFlow.collectAsState()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    // for pull down to refresh of trending gifs
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        viewModel.fetchTrendingGIFs()
        refreshing = false
    }

    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)

    // to handle currently favourite/un-favourite GIF
    var currentGifID by remember { mutableStateOf("") }

    CustomScaffold(
        topBar = {
            CustomTopBar(
                titleLabel = "Trending Gifs",
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search gifs"
                        )
                    }
                },
            )
        },
        childComposable = {
            Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                when (val trendingGIFListResponse = viewModel.trendingState.value) {
                    is APIResult.Loading -> CustomProgressIndicator()
                    is APIResult.Success -> LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 8.dp, vertical = 4.dp
                        )
                    ) {
                        trendingGIFListResponse.data?.let { gifList ->
                            items(
                                count = gifList.data.size,
                                itemContent = {
                                    ImageExample(
                                        gifHeight = gifList.data[it].images.original.height,
                                        gifUrl = gifList.data[it].images.original.url,
                                        favFunction = {
                                            if (favViewModel.isGIFFavourite(gifList.data[it].id) || currentGifID != "") {
                                                favViewModel.deleteFavouriteGIF(gifList.data[it].id)
                                                currentGifID = ""
                                                Toast.makeText(
                                                    context,
                                                    "GIF removed successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {
                                                favViewModel.insertFavouriteGIF(
                                                    FavouriteGIF(
                                                        gifURL = gifList.data[it].images.original.url,
                                                        gifIDValue = gifList.data[it].id,
                                                    )
                                                )
                                                currentGifID = gifList.data[it].id
                                                Toast.makeText(
                                                    context,
                                                    "GIF added successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        },
                                        isFav = favViewModel.isGIFFavourite(gifList.data[it].id) || currentGifID == gifList.data[it].id,
                                    )
                                },
                            )
                        }
                    }

                    is APIResult.Error -> {
                        CustomErrorComponent(additionalButtonFunction = { refresh() })
                        Toast.makeText(
                            LocalContext.current, "Error fetching trending gifs", Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        CustomErrorComponent()
                        Toast.makeText(
                            LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                PullRefreshIndicator(
                    refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter)
                )
            }
        },
    )

//    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .pointerInput(Unit) {
//                    detectTapGestures(onTap = { focusManager.clearFocus() })
//                }
//                .padding(8.dp),
//        ) {
//            NoiceTextField(
//                modifier = Modifier.focusRequester(focusRequester),
//                textFieldValue = searchValue,
//                textFieldLabel = "Search GIFs",
//                onValueChangeMethod = {
//                    viewModel.setSearchForm(it)
//                    viewModel.showSearchList = it.isNotEmpty()
//                    if (it.isEmpty()) {
//                        currentGifID = ""
//                    }
//                },
//                desiredKeyboardActions = KeyboardActions(),
//            )
//            if (viewModel.showSearchList) {
//                when (val searchGIFListResponse = viewModel.searchedState.value) {
//                    is APIResult.Loading -> CustomProgressIndicator()
//                    is APIResult.Success -> LazyColumn(
//                        contentPadding = PaddingValues(
//                            horizontal = 8.dp, vertical = 4.dp
//                        )
//                    ) {
//                        item {
//                            Text(
//                                "Searched GIFs",
//                                textAlign = TextAlign.Start,
//                                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
//                            )
//                            Spacer(modifier = Modifier.height(8.dp))
//                        }
//                        searchGIFListResponse.data?.let { gifList ->
//                            items(
//                                count = gifList.data.size,
//                                itemContent = {
//                                    ImageExample(
//                                        gifHeight = gifList.data[it].images.original.height,
//                                        gifUrl = gifList.data[it].images.original.url,
//                                        favFunction = {
//                                            if (favViewModel.isGIFFavourite(gifList.data[it].id) || currentGifID != "") {
//                                                favViewModel.deleteFavouriteGIF(gifList.data[it].id)
//                                                currentGifID = ""
//                                                Toast.makeText(
//                                                    context,
//                                                    "GIF removed successfully",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            } else {
//                                                favViewModel.insertFavouriteGIF(
//                                                    FavouriteGIF(
//                                                        gifURL = gifList.data[it].images.original.url,
//                                                        gifIDValue = gifList.data[it].id,
//                                                    )
//                                                )
//                                                currentGifID = gifList.data[it].id
//                                                Toast.makeText(
//                                                    context,
//                                                    "GIF added successfully",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            }
//                                        },
//                                        isFav = favViewModel.isGIFFavourite(gifList.data[it].id) || currentGifID == gifList.data[it].id,
//                                    )
//                                },
//                            )
//                        }
//                    }
//
//                    is APIResult.Error -> {
//                        CustomErrorComponent()
//                        Toast.makeText(
//                            LocalContext.current, "Error fetching searched gifs", Toast.LENGTH_SHORT
//                        ).show()
//                    }
//
//                    else -> {
//                        CustomErrorComponent()
//                        Toast.makeText(
//                            LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            } else {
//                when (val trendingGIFListResponse = viewModel.trendingState.value) {
//                    is APIResult.Loading -> CustomProgressIndicator()
//                    is APIResult.Success -> LazyColumn(
//                        contentPadding = PaddingValues(
//                            horizontal = 8.dp, vertical = 4.dp
//                        )
//                    ) {
//                        trendingGIFListResponse.data?.let { gifList ->
//                            items(
//                                count = gifList.data.size,
//                                itemContent = {
//                                    ImageExample(
//                                        gifHeight = gifList.data[it].images.original.height,
//                                        gifUrl = gifList.data[it].images.original.url,
//                                        favFunction = {
//                                            if (favViewModel.isGIFFavourite(gifList.data[it].id) || currentGifID != "") {
//                                                favViewModel.deleteFavouriteGIF(gifList.data[it].id)
//                                                currentGifID = ""
//                                                Toast.makeText(
//                                                    context,
//                                                    "GIF removed successfully",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            } else {
//                                                favViewModel.insertFavouriteGIF(
//                                                    FavouriteGIF(
//                                                        gifURL = gifList.data[it].images.original.url,
//                                                        gifIDValue = gifList.data[it].id,
//                                                    )
//                                                )
//                                                currentGifID = gifList.data[it].id
//                                                Toast.makeText(
//                                                    context,
//                                                    "GIF added successfully",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            }
//                                        },
//                                        isFav = favViewModel.isGIFFavourite(gifList.data[it].id) || currentGifID == gifList.data[it].id,
//                                    )
//                                },
//                            )
//                        }
//                    }
//
//                    is APIResult.Error -> {
//                        CustomErrorComponent(additionalButtonFunction = { refresh() })
//                        Toast.makeText(
//                            LocalContext.current, "Error fetching trending gifs", Toast.LENGTH_SHORT
//                        ).show()
//                    }
//
//                    else -> {
//                        CustomErrorComponent()
//                        Toast.makeText(
//                            LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
//        }
//        PullRefreshIndicator(
//            refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter)
//        )
//    }
}