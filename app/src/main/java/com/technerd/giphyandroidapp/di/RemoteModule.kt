package com.technerd.giphyandroidapp.di

import com.technerd.giphyandroidapp.core.networking.api.AppApi
import com.technerd.giphyandroidapp.features.trendinggifs.data.datasource.TrendingGIFsRemoteDataSource
import com.technerd.giphyandroidapp.features.trendinggifs.data.datasource.TrendingGIFsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideRemoteDataSource(appApi: AppApi): TrendingGIFsRemoteDataSource =
        TrendingGIFsRemoteDataSourceImpl(appApi)
}