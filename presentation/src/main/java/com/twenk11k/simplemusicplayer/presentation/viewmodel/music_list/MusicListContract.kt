package com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list

import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

class MusicListContract {

    data class MusicListViewState(
        val loading: Boolean = false,
        val noResults: Boolean = false,
        val songs: List<MusicDomainModel> = emptyList()
    )

    sealed class ViewEffect {
        data class ShowSnackBarError(val error: Int) : ViewEffect()
    }
}
