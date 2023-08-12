package com.technerd.giphyandroidapp.features.trendinggifs.domain.model

import com.google.gson.annotations.SerializedName

data class PaginationRequest(
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
) : java.io.Serializable
