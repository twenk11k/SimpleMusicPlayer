package com.twenk11k.simplemusicplayer.persistence.database.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twenk11k.simplemusicplayer.persistence.model.FavoriteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeFavorite(favoriteModel: FavoriteModel)

    @Query("SELECT title FROM favorite WHERE isFavorite = 1 LIMIT 1")
    fun fetchFavoriteMusicTitle(): Flow<String>

    @Query("DELETE FROM favorite")
    suspend fun deleteAll()
}
