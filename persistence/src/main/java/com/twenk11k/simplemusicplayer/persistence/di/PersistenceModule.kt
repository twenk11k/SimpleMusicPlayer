package com.twenk11k.simplemusicplayer.persistence.di

import android.content.Context
import androidx.room.Room
import com.twenk11k.simplemusicplayer.data.source.persistence.FavoriteDataSource
import com.twenk11k.simplemusicplayer.persistence.database.AppDatabase
import com.twenk11k.simplemusicplayer.persistence.database.favorite.FavoriteDao
import com.twenk11k.simplemusicplayer.persistence.source.favorite.FavoriteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PersistenceModule {

    @Binds
    abstract fun provideFavoriteDataSource(
        favoriteDataSourceImpl: FavoriteDataSourceImpl
    ): FavoriteDataSource

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "simplemusicplayer.db"
            )
                .build()
        }

        @Provides
        fun provideFavoriteDao(
            appDatabase: AppDatabase
        ): FavoriteDao = appDatabase.favoriteDao()
    }
}
