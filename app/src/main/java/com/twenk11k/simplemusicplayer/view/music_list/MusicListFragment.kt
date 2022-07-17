package com.twenk11k.simplemusicplayer.view.music_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.ImageLoader
import com.google.android.material.snackbar.Snackbar
import com.twenk11k.simplemusicplayer.databinding.FragmentMusicListBinding
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListContract
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListViewModel
import com.twenk11k.simplemusicplayer.view.util.collectWhenStarted
import com.twenk11k.simplemusicplayer.view.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicListFragment : Fragment() {

    private lateinit var binding: FragmentMusicListBinding

    private val viewModel by viewModels<MusicListViewModel>()

    private var adapter: MusicAdapter? = null

    private lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerViewAdapter()
        observeViewState()
        observeViewEffect()
    }

    private fun setRecyclerViewAdapter() {
        imageLoader = ImageLoader.Builder(requireContext()).build()
        adapter = MusicAdapter(imageLoader)
        binding.rvMusic.adapter = adapter
        binding.rvMusic.setHasFixedSize(true)
    }

    private fun observeViewState() {
        viewLifecycleOwner.collectWhenStarted(viewModel.viewState) { state ->
            render(state)
        }
    }

    private fun render(state: MusicListContract.MusicListViewState) {
        binding.apply {
            progressBar.isVisible = state.loading
            binding.rvMusic.visibility = View.VISIBLE
            adapter?.updateAdapter(state.songs)
        }
    }

    private fun observeViewEffect() {
        viewLifecycleOwner.collectWhenStarted(viewModel.viewEffect) {
            reactTo(it)
        }
    }

    private fun reactTo(effect: MusicListContract.ViewEffect) {
        binding.apply {
            when (effect) {
                is MusicListContract.ViewEffect.ShowSnackBarError -> {
                    progressBar.visibility = View.GONE
                    showSnackBar(
                        effect.error,
                        Snackbar.LENGTH_SHORT
                    )
                }
            }
        }
    }
}
