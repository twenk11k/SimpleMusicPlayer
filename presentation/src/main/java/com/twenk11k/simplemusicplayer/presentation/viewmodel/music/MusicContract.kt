package com.twenk11k.simplemusicplayer.presentation.viewmodel.music

import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

class MusicContract {

    sealed class MusicEvent {
        object SwipeRefresh : MusicEvent()
    }

    sealed class ViewEffect {
        data class ShowSnackBarError(val error: Int) : ViewEffect()
    }

    data class MusicViewState(
        val loading: Boolean = false,
        val loadingFavorite: Boolean = false,
        val songs: List<MusicDomainModel> = emptyList()
    )
}
