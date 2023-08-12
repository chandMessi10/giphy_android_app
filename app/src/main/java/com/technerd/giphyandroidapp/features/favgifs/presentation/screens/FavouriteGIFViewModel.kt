package com.technerd.giphyandroidapp.features.favgifs.presentation.screens

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.technerd.giphyandroidapp.features.favgifs.domain.model.FavouriteGIF
import com.technerd.giphyandroidapp.features.favgifs.domain.repository.FavouriteGIFRepository
import com.technerd.giphyandroidapp.features.favgifs.domain.util.FavouriteGIFRoomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteGIFViewModel @Inject constructor(
    application: Application,
) : ViewModel() {
    lateinit var allFavouriteGIFs: LiveData<List<FavouriteGIF>>
    private lateinit var repository: FavouriteGIFRepository

    var isLoading = mutableStateOf(true)

    init {
        fetchSavedData(application)
    }

    fun fetchSavedData(application: Application) {
        val favouriteGIFDB = FavouriteGIFRoomDatabase.getInstance(application)
        val favouriteGIFDao = favouriteGIFDB.favouriteGIFDao()
        repository = FavouriteGIFRepository(favouriteGIFDao)
        allFavouriteGIFs = repository.allFavouriteGIFs
        isLoading.value = false
    }

    fun insertFavouriteGIF(favouriteGIF: FavouriteGIF) {
        repository.insertGIF(favouriteGIF)
    }

    fun deleteFavouriteGIF(favouriteGIFId: String) {
        repository.deleteGIF(favouriteGIFId)
    }

    fun isGIFFavourite(currentGifIDValue: String): Boolean {
        val allFavouriteGIFs = allFavouriteGIFs.value ?: return false
        allFavouriteGIFs.forEach { favouriteGif ->
            if (favouriteGif.gifIDValue == currentGifIDValue) {
                return true
            }
        }
        return false
    }
}