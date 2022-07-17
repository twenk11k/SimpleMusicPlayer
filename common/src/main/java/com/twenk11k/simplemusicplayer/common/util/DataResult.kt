package com.twenk11k.simplemusicplayer.common.util

sealed class DataResult<out T> {
    class Success<T>(val data: T) : DataResult<T>()
    data class Error<T>(val exception: Exception) : DataResult<T>()
}
