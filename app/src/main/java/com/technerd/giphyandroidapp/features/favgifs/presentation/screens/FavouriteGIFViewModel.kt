package com.technerd.giphyandroidapp.features.favgifs.presentation.screens

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.technerd.giphyandroidapp.features.favgifs.domain.model.FavouriteGIF
import com.technerd.giphyandroidapp.features.favgifs.domain.repository.FavouriteGIFRepository
import com.technerd.giphyandroidapp.features.favgifs.domain.util.FavouriteGIFRoomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteGIFViewModel @Inject constructor(application: Application) : ViewModel() {
    val allFavouriteGIFs: LiveData<List<FavouriteGIF>>
    private val repository: FavouriteGIFRepository
    val loadingState = MutableLiveData(true)
    val successState = MutableLiveData(false)
    val failureState = MutableLiveData(false)
    private val viewLifecycleOwner = MutableLiveData<LifecycleOwner>()

    init {
        val favouriteGIFDB = FavouriteGIFRoomDatabase.getInstance(application)
        val favouriteGIFDao = favouriteGIFDB.favouriteGIFDao()
        repository = FavouriteGIFRepository(favouriteGIFDao)
        allFavouriteGIFs = repository.allFavouriteGIFs
    }

    fun insertFavouriteGIF(favouriteGIF: FavouriteGIF) {
        repository.insertGIF(favouriteGIF)
    }

    fun deleteFavouriteGIF(favouriteGIFId: String) {
        repository.deleteGIF(favouriteGIFId)
    }

    fun getLoadingState(): LiveData<Boolean> {
        return loadingState
    }

    fun getSuccessState(): LiveData<Boolean> {
        return successState
    }

    fun getFailureState(): LiveData<Boolean> {
        return failureState
    }
}