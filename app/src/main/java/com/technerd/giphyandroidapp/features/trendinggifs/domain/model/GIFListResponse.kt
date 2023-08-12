package com.technerd.giphyandroidapp.features.trendinggifs.domain.model

import com.google.gson.annotations.SerializedName

data class GIFListResponse(
    @SerializedName("data") val data: List<GIFDetail>
) : java.io.Serializable
