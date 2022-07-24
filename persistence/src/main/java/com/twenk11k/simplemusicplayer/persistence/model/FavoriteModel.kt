package com.twenk11k.simplemusicplayer.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val isFavorite: Boolean
)
