package com.twenk11k.simplemusicplayer.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.twenk11k.simplemusicplayer.data.source.network.music.MusicNetworkDataSource
import com.twenk11k.simplemusicplayer.network.apiservice.MusicApiService
import com.twenk11k.simplemusicplayer.network.source.MusicNetworkDataSourceImpl
import com.twenk11k.simplemusicplayer.network.util.NetworkConstants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            return Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_ENDPOINT)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        }

        @Provides
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @Singleton
        @Provides
        fun provideMusicApiService(retrofit: Retrofit): MusicApiService =
            retrofit.create(MusicApiService::class.java)
    }

    @Binds
    abstract fun provideMusicNetworkDataSource(
        musicNetworkDataSourceImpl: MusicNetworkDataSourceImpl
    ): MusicNetworkDataSource
}
