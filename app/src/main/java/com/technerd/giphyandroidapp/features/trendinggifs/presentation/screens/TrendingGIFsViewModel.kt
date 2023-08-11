package com.technerd.giphyandroidapp.features.trendinggifs.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.TrendingGIFListResponse
import com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase.GetTrendingGIFs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingGIFsViewModel @Inject constructor(
    private val getTrendingGIFs: GetTrendingGIFs
) : ViewModel() {
    private val _trendingGIFState = mutableStateOf<APIResult<TrendingGIFListResponse>>(
        APIResult.Loading()
    )
    val trendingState: State<APIResult<TrendingGIFListResponse>> = _trendingGIFState
    private val _searchInputFlow = MutableStateFlow("")
    val searchInputFlow: StateFlow<String> get() = _searchInputFlow

    init {
        fetchTrendingGIFs()
    }

    fun setSearchForm(searchQuery: String) {
        _searchInputFlow.value = searchQuery
    }

    fun fetchTrendingGIFs() {
        viewModelScope.launch {
            _trendingGIFState.value = getTrendingGIFs()
        }
    }
}