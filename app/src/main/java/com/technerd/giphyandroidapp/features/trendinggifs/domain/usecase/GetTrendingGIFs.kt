package com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase

import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.PaginationRequest
import com.technerd.giphyandroidapp.features.trendinggifs.domain.repository.TrendingGIFsRepository

class GetTrendingGIFs(private val tendingGIFsRepository: TrendingGIFsRepository) {
    suspend operator fun invoke(paginationRequest: PaginationRequest) =
        tendingGIFsRepository.getTrendingGIFs(paginationRequest)
}