package com.technerd.giphyandroidapp.features.trendinggifs.domain.model

import com.google.gson.annotations.SerializedName

data class GIFDetail(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: Images,
) : java.io.Serializable

data class Images(
    @SerializedName("original") val original: ImageDetail,
    @SerializedName("fixed_height") val fixedHeight: ImageDetail,
) : java.io.Serializable

data class ImageDetail(
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
) : java.io.Serializable
