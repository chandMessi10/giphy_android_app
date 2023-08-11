package com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
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
import com.technerd.giphyandroidapp.core.components.CustomErrorComponent
import com.technerd.giphyandroidapp.core.components.CustomProgressIndicator
import com.technerd.giphyandroidapp.core.components.ImageExample
import com.technerd.giphyandroidapp.core.components.NoiceTextField
import com.technerd.giphyandroidapp.core.util.APIResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrendingGIFsScreen(viewModel: TrendingGIFsViewModel = hiltViewModel()) {
    val searchValue by viewModel.searchInputFlow.collectAsState()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    // for pull down to refresh
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        viewModel.fetchTrendingGIFs()
        refreshing = false
    }

    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
            .padding(8.dp),
    ) {
        NoiceTextField(
            modifier = Modifier.focusRequester(focusRequester),
            textFieldValue = searchValue,
            textFieldLabel = "Search GIFs",
            onValueChangeMethod = {
                viewModel.setSearchForm(it)
            },
            desiredKeyboardActions = KeyboardActions(),
        )
        when (val trendingGIFListResponse = viewModel.trendingState.value) {
            is APIResult.Loading -> CustomProgressIndicator()
            is APIResult.Success -> Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        horizontal = 8.dp, vertical = 4.dp
                    )
                ) {
                    trendingGIFListResponse.data?.let { gifList ->
                        items(
                            count = gifList.data.size,
                            itemContent = {
                                ImageExample(
                                    gifHeight = gifList.data[it].images.fixedHeight.height,
                                    gifUrl = gifList.data[it].images.fixedHeight.url,
                                )
                            },
                        )
                    }
                }
                PullRefreshIndicator(
                    refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter)
                )
            }

            is APIResult.Error -> {
                CustomErrorComponent()
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
    }
}