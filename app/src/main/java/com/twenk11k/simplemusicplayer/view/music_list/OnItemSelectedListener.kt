package com.twenk11k.simplemusicplayer.view.music_list

import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

interface OnItemSelectedListener {
    fun navigateToMusicPlay(item: MusicDomainModel)
    fun setFavorite(title: String)
    fun clearFavorites()
}
