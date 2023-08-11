package com.technerd.giphyandroidapp.features.trendinggifs.data.datasource

import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.TrendingGIFListResponse
import retrofit2.Response

interface TrendingGIFsRemoteDataSource {
    suspend fun getTrendingGIFs(): Response<TrendingGIFListResponse>
}