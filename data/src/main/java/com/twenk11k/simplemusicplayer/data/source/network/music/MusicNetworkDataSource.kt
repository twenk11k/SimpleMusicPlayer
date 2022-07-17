package com.twenk11k.simplemusicplayer.data.source.network.music

import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.data.model.MusicDataModel

interface MusicNetworkDataSource {

    suspend fun getSongs(): DataResult<List<MusicDataModel>>
}
