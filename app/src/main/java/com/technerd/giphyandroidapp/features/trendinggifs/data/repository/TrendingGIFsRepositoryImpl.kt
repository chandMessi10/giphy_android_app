package com.technerd.giphyandroidapp.features.trendinggifs.data.repository

import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.core.util.responseToRequest
import com.technerd.giphyandroidapp.features.trendinggifs.data.datasource.TrendingGIFsRemoteDataSource
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.TrendingGIFListResponse
import com.technerd.giphyandroidapp.features.trendinggifs.domain.repository.TrendingGIFsRepository
import java.io.IOException

class TrendingGIFsRepositoryImpl(private val trendingGIFsRemoteDataSource: TrendingGIFsRemoteDataSource) :
    TrendingGIFsRepository {
    override suspend fun getTrendingGIFs(): APIResult<TrendingGIFListResponse> {
        return try {
            val response = trendingGIFsRemoteDataSource.getTrendingGIFs()
            responseToRequest(response)
        } catch (e: IOException) {
            APIResult.Error(message = "${e.message}")
        }
    }
}