package com.twenk11k.simplemusicplayer.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.twenk11k.simplemusicplayer.persistence.database.favorite.FavoriteDao
import com.twenk11k.simplemusicplayer.persistence.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
