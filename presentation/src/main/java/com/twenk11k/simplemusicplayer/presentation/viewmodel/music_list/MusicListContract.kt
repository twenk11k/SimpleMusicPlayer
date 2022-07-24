package com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list

import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

class MusicListContract {

    sealed class MusicListEvent {
        object SwipeRefresh : MusicListEvent()
    }

    sealed class ViewEffect {
        data class ShowSnackBarError(val error: Int) : ViewEffect()
    }

    data class MusicListViewState(
        val loading: Boolean = false,
        val loadingFavorite: Boolean = false,
        val songs: List<MusicDomainModel> = emptyList()
    )
}
