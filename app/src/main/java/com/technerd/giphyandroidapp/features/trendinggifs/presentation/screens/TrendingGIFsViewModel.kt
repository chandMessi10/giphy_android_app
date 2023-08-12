package com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.GIFListResponse
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.PaginationRequest
import com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase.GetSearchedGIFs
import com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase.GetTrendingGIFs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingGIFsViewModel @Inject constructor(
    private val getTrendingGIFs: GetTrendingGIFs,
    private val getSearchedGIFs: GetSearchedGIFs,
) : ViewModel() {
    // TRENDING GIFS
    private val _trendingGIFState = mutableStateOf<APIResult<GIFListResponse>>(
        APIResult.Loading()
    )
    val trendingState: State<APIResult<GIFListResponse>> = _trendingGIFState
    private val _searchInputFlow = MutableStateFlow("")
    val searchInputFlow: StateFlow<String> get() = _searchInputFlow

    // SEARCHED GIFS
    var showSearchList by mutableStateOf(false)
    private val _searchedGIFState = mutableStateOf<APIResult<GIFListResponse>>(
        APIResult.Loading()
    )
    val searchedState: State<APIResult<GIFListResponse>> = _searchedGIFState

    init {
        fetchTrendingGIFs()
    }

    fun setSearchForm(searchQuery: String) {
        _searchInputFlow.value = searchQuery
        fetchSearchedGIFs(searchQuery)
    }

    fun fetchTrendingGIFs() {
        viewModelScope.launch {
            _trendingGIFState.value = getTrendingGIFs(PaginationRequest(limit = 10, offset = 0))
        }
    }

    private fun fetchSearchedGIFs(searchQuery: String) {
        viewModelScope.launch {
            _searchedGIFState.value = getSearchedGIFs(searchQuery)
        }
    }
}