package com.twenk11k.simplemusicplayer.view.music_play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.twenk11k.simplemusicplayer.databinding.FragmentMusicPlayBinding

class MusicPlayFragment : Fragment() {

    private lateinit var binding: FragmentMusicPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicPlayBinding.inflate(inflater, container, false)
        return binding.root
    }
}
