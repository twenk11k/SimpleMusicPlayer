package com.twenk11k.simplemusicplayer.domain.model

data class MusicDomainModel(
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int
)
