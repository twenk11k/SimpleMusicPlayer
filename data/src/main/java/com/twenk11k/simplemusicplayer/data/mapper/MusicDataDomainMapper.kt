package com.twenk11k.simplemusicplayer.data.mapper

import com.twenk11k.simplemusicplayer.common.mapper.Mapper
import com.twenk11k.simplemusicplayer.data.model.MusicDataModel
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel
import javax.inject.Inject

class MusicDataDomainMapper @Inject constructor() : Mapper<MusicDataModel, MusicDomainModel> {
    override fun from(i: MusicDataModel): MusicDomainModel {
        return MusicDomainModel(
            title = i.title,
            audio = i.audio,
            cover = i.cover,
            totalDurationMs = i.totalDurationMs
        )
    }

    override fun to(o: MusicDomainModel): MusicDataModel {
        return MusicDataModel(
            title = o.title,
            audio = o.audio,
            cover = o.cover,
            totalDurationMs = o.totalDurationMs
        )
    }
}
