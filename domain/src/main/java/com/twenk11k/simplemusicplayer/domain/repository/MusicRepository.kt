package com.twenk11k.simplemusicplayer.domain.repository

import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

interface MusicRepository {
    suspend fun getSongs(): DataResult<List<MusicDomainModel>>
}
