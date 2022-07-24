package com.twenk11k.simplemusicplayer.domain.model

data class MusicDomainModel(
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int,
    // this variable will be updated if the music is set as favorite from the database
    var isFavorite: Boolean = false
)
