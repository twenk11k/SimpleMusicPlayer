package com.twenk11k.simplemusicplayer.domain.usecase

import com.twenk11k.simplemusicplayer.common.exception.NetworkException
import com.twenk11k.simplemusicplayer.common.qualifier.IoDispatcher
import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel
import com.twenk11k.simplemusicplayer.domain.repository.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicUseCase @Inject constructor(
    private val repository: MusicRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): DataResult<List<MusicDomainModel>> {
        return when (
            val result = withContext(dispatcher) {
                repository.getSongs()
            }
        ) {
            is DataResult.Success -> {
                val list = result.data
                if (list.isEmpty()) {
                    return DataResult.Error(NetworkException.NoResults)
                }
                DataResult.Success(list)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }
}
