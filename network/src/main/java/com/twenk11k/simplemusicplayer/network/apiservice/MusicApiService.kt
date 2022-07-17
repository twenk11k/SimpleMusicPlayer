package com.twenk11k.simplemusicplayer.network.apiservice

import com.twenk11k.simplemusicplayer.network.model.response.BaseResponse
import com.twenk11k.simplemusicplayer.network.model.response.MusicNetworkModel
import com.twenk11k.simplemusicplayer.network.util.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET

interface MusicApiService {

    @GET(NetworkConstants.SONGS_ENDPOINT)
    suspend fun getSongs(): Response<BaseResponse<MusicNetworkModel>>
}
