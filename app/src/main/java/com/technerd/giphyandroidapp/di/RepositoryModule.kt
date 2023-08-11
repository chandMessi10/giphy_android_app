package com.technerd.giphyandroidapp.di

import com.technerd.giphyandroidapp.features.trendinggifs.data.datasource.TrendingGIFsRemoteDataSource
import com.technerd.giphyandroidapp.features.trendinggifs.data.repository.TrendingGIFsRepositoryImpl
import com.technerd.giphyandroidapp.features.trendinggifs.domain.repository.TrendingGIFsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMoviesRepository(trendingGIFsRemoteDataSource: TrendingGIFsRemoteDataSource): TrendingGIFsRepository =
        TrendingGIFsRepositoryImpl(trendingGIFsRemoteDataSource)
}