package com.technerd.giphyandroidapp.di

import com.technerd.giphyandroidapp.features.trendinggifs.domain.repository.TrendingGIFsRepository
import com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase.GetSearchedGIFs
import com.technerd.giphyandroidapp.features.trendinggifs.domain.usecase.GetTrendingGIFs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetTrendingGIFsUseCase(trendingGIFsRepository: TrendingGIFsRepository) =
        GetTrendingGIFs(trendingGIFsRepository)

    @Provides
    fun provideGetSearchedGIFsUseCase(trendingGIFsRepository: TrendingGIFsRepository) =
        GetSearchedGIFs(trendingGIFsRepository)
}