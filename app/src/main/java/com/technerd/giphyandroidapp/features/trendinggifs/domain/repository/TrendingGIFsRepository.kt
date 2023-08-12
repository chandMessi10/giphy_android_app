package com.technerd.giphyandroidapp.features.trendinggifs.domain.repository

import com.technerd.giphyandroidapp.core.util.APIResult
import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.GIFListResponse

interface TrendingGIFsRepository {
    suspend fun getTrendingGIFs(): APIResult<GIFListResponse>
    suspend fun getSearchedGIFs(queryText: String): APIResult<GIFListResponse>
}