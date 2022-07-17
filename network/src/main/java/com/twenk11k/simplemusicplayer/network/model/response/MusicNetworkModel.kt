package com.twenk11k.simplemusicplayer.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusicNetworkModel(
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "audio") val audio: String?,
    @field:Json(name = "cover") val cover: String?,
    @field:Json(name = "totalDurationMs") val totalDurationMs: Int?
)
