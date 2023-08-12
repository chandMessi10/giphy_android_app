package com.technerd.giphyandroidapp.features.trendinggifs.data.datasource

import com.technerd.giphyandroidapp.core.networking.api.AppApi
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.PaginationRequest

class TrendingGIFsRemoteDataSourceImpl(private val appApi: AppApi) : TrendingGIFsRemoteDataSource {
    override suspend fun getTrendingGIFs(paginationRequest: PaginationRequest) =
        appApi.getTrendingGIFs(
            apiKey = "lj0qsiizKQAqk1ynadomUYNnhTD3Zm5b",
            limit = paginationRequest.limit,
            offset = paginationRequest.offset,
            rating = "g",
            bundle = "messaging_non_clips"
        )

    override suspend fun getSearchedGIFs(queryString: String) = appApi.getSearchedGIFs(
        q = queryString,
        apiKey = "lj0qsiizKQAqk1ynadomUYNnhTD3Zm5b",
        limit = 10,
        offset = 0,
        rating = "g",
        lang = "en",
        bundle = "messaging_non_clips"
    )
}