package com.twenk11k.simplemusicplayer.di

import com.twenk11k.simplemusicplayer.common.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    internal fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
