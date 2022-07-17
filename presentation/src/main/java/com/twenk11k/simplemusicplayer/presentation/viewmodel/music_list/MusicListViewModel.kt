package com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twenk11k.simplemusicplayer.common.mapper.getStringResId
import com.twenk11k.simplemusicplayer.common.util.DataResult
import com.twenk11k.simplemusicplayer.domain.usecase.MusicUseCase
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicListViewModel @Inject constructor(
    private val useCase: MusicUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MusicListViewState())
    val viewState: StateFlow<MusicListViewState> get() = _viewState

    private val _viewEffect: Channel<ViewEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    init {
        requestSongs()
    }

    private fun requestSongs() {
        setState { copy(loading = true) }
        viewModelScope.launch {
            when (val result = useCase()) {
                is DataResult.Success -> {
                    setState { copy(noResults = false, loading = false, songs = result.data) }
                }
                is DataResult.Error -> {
                    val error = result.exception.getStringResId()
                    setEffect { ViewEffect.ShowSnackBarError(error) }
                }
            }
        }
    }

    /**
     * Set new ui state
     */
    private fun setState(reduce: MusicListViewState.() -> MusicListViewState) {
        val newState = viewState.value.reduce()
        _viewState.value = newState
    }

    /**
     * Set new effect
     */
    private fun setEffect(builder: () -> ViewEffect) {
        val effectValue = builder()
        setState { copy(loading = false) }
        viewModelScope.launch { _viewEffect.send(effectValue) }
    }

    /**
     * Handle events
     */
    fun setEvent(event: MusicListEvent) {
        when (event) {
            is MusicListEvent.SwipeRefresh -> onRefresh()
        }
    }

    /**
     * Start refreshing songs
     */
    private fun onRefresh() {
        requestSongs()
    }
}
