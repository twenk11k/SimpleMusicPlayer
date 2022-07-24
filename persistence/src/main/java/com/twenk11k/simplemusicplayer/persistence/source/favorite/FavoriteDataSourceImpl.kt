package com.twenk11k.simplemusicplayer.persistence.source.favorite

import com.twenk11k.simplemusicplayer.data.source.persistence.FavoriteDataSource
import com.twenk11k.simplemusicplayer.persistence.database.favorite.FavoriteDao
import com.twenk11k.simplemusicplayer.persistence.model.FavoriteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteDataSource {

    override suspend fun storeFavoriteMusic(title: String) {
        favoriteDao.deleteAll()
        favoriteDao.storeFavorite(FavoriteModel(title = title, isFavorite = true))
    }

    override suspend fun deleteAll() {
        favoriteDao.deleteAll()
    }

    override fun fetchFavoriteMusicTitle(): Flow<String> =
        favoriteDao.fetchFavoriteMusicTitle()
}
