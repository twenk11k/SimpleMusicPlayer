package com.twenk11k.simplemusicplayer.data.di

import com.twenk11k.simplemusicplayer.data.repository.MusicRepositoryImpl
import com.twenk11k.simplemusicplayer.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMusicRepository(
        repository: MusicRepositoryImpl
    ): MusicRepository
}
