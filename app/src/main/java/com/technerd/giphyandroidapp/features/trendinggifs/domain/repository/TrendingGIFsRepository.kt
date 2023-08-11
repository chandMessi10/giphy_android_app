package com.technerd.giphyandroidapp.features.trendinggifs.domain.repository

import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.TrendingGIFListResponse

interface TrendingGIFsRepository {
    suspend fun getTrendingGIFs(): APIResult<TrendingGIFListResponse>
}