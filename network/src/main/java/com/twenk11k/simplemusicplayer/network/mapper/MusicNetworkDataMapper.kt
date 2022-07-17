package com.twenk11k.simplemusicplayer.network.mapper

import com.twenk11k.simplemusicplayer.common.mapper.Mapper
import com.twenk11k.simplemusicplayer.data.model.MusicDataModel
import com.twenk11k.simplemusicplayer.network.model.response.MusicNetworkModel
import javax.inject.Inject

class MusicNetworkDataMapper @Inject constructor() : Mapper<MusicNetworkModel, MusicDataModel> {

    override fun from(i: MusicNetworkModel): MusicDataModel {
        return MusicDataModel(
            title = i.title.orEmpty(),
            audio = i.audio.orEmpty(),
            cover = i.cover.orEmpty(),
            totalDurationMs = i.totalDurationMs ?: 0
        )
    }

    override fun to(o: MusicDataModel): MusicNetworkModel {
        return MusicNetworkModel(
            title = o.title,
            audio = o.audio,
            cover = o.cover,
            totalDurationMs = o.totalDurationMs
        )
    }
}
