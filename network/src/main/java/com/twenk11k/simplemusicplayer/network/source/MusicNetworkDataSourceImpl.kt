package com.twenk11k.simplemusicplayer.network.source

import com.twenk11k.simplemusicplayer.common.mapper.ExceptionMapper
import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.data.model.MusicDataModel
import com.twenk11k.simplemusicplayer.data.source.network.music.MusicNetworkDataSource
import com.twenk11k.simplemusicplayer.network.apiservice.MusicApiService
import com.twenk11k.simplemusicplayer.network.mapper.MusicNetworkDataMapper
import javax.inject.Inject

class MusicNetworkDataSourceImpl @Inject constructor(
    private val apiService: MusicApiService,
    private val musicNetworkDataMapper: MusicNetworkDataMapper,
    private val exceptionMapper: ExceptionMapper
) : MusicNetworkDataSource {

    override suspend fun getSongs(): DataResult<List<MusicDataModel>> {
        return try {
            DataResult.Success(
                apiService.getSongs().let {
                    musicNetworkDataMapper.fromList(it.body()?.data!!)
                }
            )
        } catch (exception: Exception) {
            DataResult.Error(exceptionMapper.mapError(exception))
        }
    }
}
