package com.technerd.giphyandroidapp.features.trendinggifs.domain.model

import com.google.gson.annotations.SerializedName

data class GIFDetail(
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String,
    @SerializedName("embed_url") val embedURL: String,
    @SerializedName("images") val images: Images,
) : java.io.Serializable

data class Images(
    @SerializedName("fixed_height") val fixedHeight: FixedHeightImageDetail,
) : java.io.Serializable

data class FixedHeightImageDetail(
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
) : java.io.Serializable
