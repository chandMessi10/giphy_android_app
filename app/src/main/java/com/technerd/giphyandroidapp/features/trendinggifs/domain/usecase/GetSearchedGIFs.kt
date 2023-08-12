package com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase

import com.technerd.giphyandroidapp.features.trendinggifs.domain.repository.TrendingGIFsRepository

class GetSearchedGIFs(private val tendingGIFsRepository: TrendingGIFsRepository) {
    suspend operator fun invoke(query: String) =
        tendingGIFsRepository.getSearchedGIFs(queryText = query)
}