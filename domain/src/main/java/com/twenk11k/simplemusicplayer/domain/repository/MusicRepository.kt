package com.twenk11k.simplemusicplayer.domain.repository

import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    suspend fun getSongs(): DataResult<List<MusicDomainModel>>
    suspend fun storeFavoriteMusic(title: String)
    suspend fun getFavoriteMusicTitle(): Flow<String>
    suspend fun clearFavorites()
}
