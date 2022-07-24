package com.twenk11k.simplemusicplayer.data.source.persistence

import kotlinx.coroutines.flow.Flow

interface FavoriteDataSource {
    suspend fun deleteAll()
    suspend fun storeFavoriteMusic(title: String)
    fun fetchFavoriteMusicTitle(): Flow<String>
}
