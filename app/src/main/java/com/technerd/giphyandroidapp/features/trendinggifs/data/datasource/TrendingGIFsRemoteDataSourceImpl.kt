package com.technerd.giphyandroidapp.features.trendinggifs.data.datasource

import com.technerd.giphyandroidapp.core.networking.api.AppApi
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.TrendingGIFListResponse
import retrofit2.Response

class TrendingGIFsRemoteDataSourceImpl(private val trendingApi: AppApi) :
    TrendingGIFsRemoteDataSource {
    override suspend fun getTrendingGIFs() = trendingApi.getTrendingGIFs(
        apiKey = "lj0qsiizKQAqk1ynadomUYNnhTD3Zm5b",
        limit = 10,
        offset = 0,
        rating = "g",
        bundle = "messaging_non_clips"
    )
}