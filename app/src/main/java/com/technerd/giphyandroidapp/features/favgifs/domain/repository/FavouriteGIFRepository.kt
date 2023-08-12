package com.technerd.giphyandroidapp.features.favgifs.domain.repository

import androidx.lifecycle.LiveData
import com.technerd.giphyandroidapp.features.favgifs.domain.model.FavouriteGIF
import com.technerd.giphyandroidapp.features.favgifs.domain.util.FavouriteGIFDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteGIFRepository(private val favGIFDao: FavouriteGIFDao) {
    val allFavouriteGIFs: LiveData<List<FavouriteGIF>> = favGIFDao.getAllFavouriteGIFs()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertGIF(newFavouriteGIF: FavouriteGIF) {
        coroutineScope.launch(Dispatchers.IO) {
            favGIFDao.insertGIF(newFavouriteGIF)
        }
    }

    fun deleteGIF(gifID: String) {
        coroutineScope.launch(Dispatchers.IO) {
            favGIFDao.deleteGIF(gifID)
        }
    }
}