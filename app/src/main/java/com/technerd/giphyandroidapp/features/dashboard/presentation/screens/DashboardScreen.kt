package com.technerd.giphyandroidapp.features.dashboard.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.technerd.giphyandroidapp.core.components.CustomScaffold
import com.technerd.giphyandroidapp.features.dashboard.domain.model.DashboardItemModel
import com.technerd.giphyandroidapp.features.favgifs.presentation.screens.FavouriteGifsScreen
import com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens.TrendingGIFsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen() {
    val tabs = listOf(
        DashboardItemModel(title = "Trending", screen = { TrendingGIFsScreen() }),
        DashboardItemModel(title = "Favourite", screen = { FavouriteGifsScreen() }),
    )
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        tabs.size
    }
    val coroutineScope = rememberCoroutineScope()

    CustomScaffold(
        childComposable = {
            Column {
                TabRow(
                    selectedTabIndex = pagerState.currentPage
                ) {
                    tabs.forEachIndexed { index, item ->
                        Tab(
                            modifier = Modifier.height(60.dp),
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.scrollToPage(index) } },
                            text = {
                                Text(
                                    text = item.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                        )
                    }
                }
                HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                    tabs[page].screen()
                }
            }
        },
    )
}