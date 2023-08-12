package com.technerd.giphyandroidapp.core.networking.api

import com.technerd.giphyandroidapp.features.trendinggifs.domain.model.GIFListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {
    @GET("trending")
    suspend fun getTrendingGIFs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("bundle") bundle: String,
    ): Response<GIFListResponse>

    @GET("search")
    suspend fun getSearchedGIFs(
        @Query("api_key") apiKey: String,
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String,
        @Query("bundle") bundle: String,
    ): Response<GIFListResponse>
}