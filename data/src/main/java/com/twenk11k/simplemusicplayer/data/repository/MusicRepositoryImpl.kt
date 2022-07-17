package com.twenk11k.simplemusicplayer.data.repository

import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.data.mapper.MusicDataDomainMapper
import com.twenk11k.simplemusicplayer.data.source.network.music.MusicNetworkDataSource
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel
import com.twenk11k.simplemusicplayer.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicNetworkDataSource: MusicNetworkDataSource,
    private val musicDataDomainMapper: MusicDataDomainMapper
) : MusicRepository {
    override suspend fun getSongs(): DataResult<List<MusicDomainModel>> {
        return when (val result = musicNetworkDataSource.getSongs()) {
            is DataResult.Success -> DataResult.Success(
                musicDataDomainMapper.fromList(result.data)
            )
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}
