package com.technerd.giphyandroidapp.features.favgifs.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favgifs")
class FavouriteGIF {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gifId")
    var gifId: Int = 0

    @ColumnInfo(name = "gifUrl")
    var gifURL: String = ""

    @ColumnInfo(name = "gifIDValue")
    var gifIDValue: String = ""

    constructor() {}

    constructor(gifURL: String, gifIDValue: String) {
        this.gifId = gifId
        this.gifIDValue = gifIDValue
        this.gifURL = gifURL
    }
}