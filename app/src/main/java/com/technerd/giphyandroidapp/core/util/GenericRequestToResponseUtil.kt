package com.technerd.giphyandroidapp.core.util

import retrofit2.Response

fun <T> responseToRequest(response: Response<T>): APIResult<T> {
    return if (response.isSuccessful) {
        response.body()?.let { result ->
            APIResult.Success(result)
        } ?: APIResult.Error("Response body is null")
    } else {
        APIResult.Error(response.message() ?: "Other exception")
    }
}