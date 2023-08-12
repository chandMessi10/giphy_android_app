package com.technerd.giphyandroidapp.features.favgifs.domain.util

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.technerd.giphyandroidapp.features.favgifs.domain.model.FavouriteGIF

@Dao
interface FavouriteGIFDao {
    @Insert
    fun insertGIF(gif: FavouriteGIF)

    @Query("DELETE FROM favgifs WHERE gifID = :gifId")
    fun deleteGIF(gifId: String)

    @Query("SELECT * FROM favgifs")
    fun getAllFavouriteGIFs(): LiveData<List<FavouriteGIF>>
}