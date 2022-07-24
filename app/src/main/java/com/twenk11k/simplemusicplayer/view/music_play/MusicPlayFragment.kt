package com.twenk11k.simplemusicplayer.view.music_play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.twenk11k.simplemusicplayer.databinding.FragmentMusicPlayBinding
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.time.DurationFormatUtils

@AndroidEntryPoint
class MusicPlayFragment : Fragment() {

    private lateinit var binding: FragmentMusicPlayBinding

    private val viewModel by viewModels<MusicViewModel>()

    private val args: MusicPlayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {
        binding.run {
            args.let {
                txtTitle.text = it.title
                with(imgCover) {
                    load(it.cover)
                }
                seekBar.max = it.totalDurationMs
                txtDurationTotal.text =
                    DurationFormatUtils.formatDuration(it.totalDurationMs.toLong(), "m:ss")
                handleFavorite(it.isFavorite, it.title)
            }
        }
    }

    private fun handleFavorite(isFavorite: Boolean, title: String) {
        binding.imgFavorite.run {
            this.isSelected = isFavorite
            // TODO handle click
        }
    }
}
