package com.technerd.giphyandroidapp.features.trendinggifs.domain.repository

import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.GIFListResponse
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.PaginationRequest

interface TrendingGIFsRepository {
    suspend fun getTrendingGIFs(paginationRequest: PaginationRequest): APIResult<GIFListResponse>
    suspend fun getSearchedGIFs(queryText: String): APIResult<GIFListResponse>
}