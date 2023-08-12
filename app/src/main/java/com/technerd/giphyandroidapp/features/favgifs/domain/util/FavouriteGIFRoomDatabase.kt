package com.technerd.giphyandroidapp.features.favgifs.domain.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technerd.giphyandroidapp.features.favgifs.domain.model.FavouriteGIF

@Database(entities = [(FavouriteGIF::class)], version = 2)
abstract class FavouriteGIFRoomDatabase : RoomDatabase() {
    abstract fun favouriteGIFDao(): FavouriteGIFDao

    companion object {
        private var INSTANCE: FavouriteGIFRoomDatabase? = null

        fun getInstance(context: Context): FavouriteGIFRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavouriteGIFRoomDatabase::class.java,
                        "gif_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}