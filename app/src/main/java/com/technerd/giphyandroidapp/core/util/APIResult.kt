package com.technerd.giphyandroidapp.core.util

sealed class APIResult<T>(
    val data: T? = null,
    val message: String? = null,
) : java.io.Serializable {
    class Initial<T> : APIResult<T>()
    class Success<T>(data: T?) :
        APIResult<T>(data)

    class Loading<T>(data: T? = null) : APIResult<T>(data)
    class Error<T>(message: String?, data: T? = null) :
        APIResult<T>(data, message)
}
