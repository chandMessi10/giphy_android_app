package com.technerd.giphyandroidapp.features.trendinggifs.data.datasource

import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.GIFListResponse
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.PaginationRequest
import retrofit2.Response

interface TrendingGIFsRemoteDataSource {
    suspend fun getTrendingGIFs(paginationRequest: PaginationRequest): Response<GIFListResponse>
    suspend fun getSearchedGIFs(queryString: String): Response<GIFListResponse>
}