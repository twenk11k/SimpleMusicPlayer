package com.twenk11k.simplemusicplayer.view.music_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.ImageLoader
import com.google.android.material.snackbar.Snackbar
import com.twenk11k.simplemusicplayer.databinding.FragmentMusicListBinding
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListContract
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListViewModel
import com.twenk11k.simplemusicplayer.view.util.collectWhenStarted
import com.twenk11k.simplemusicplayer.view.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

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
        setSwipeRefresh()
        observeViewState()
        observeViewEffect()
    }

    private fun setSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener(this)
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
            swipeRefresh.isRefreshing = state.loading
            binding.rvMusic.visibility = View.VISIBLE
            adapter?.submitList(state.songs)
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
                    showSnackBar(
                        effect.error,
                        Snackbar.LENGTH_SHORT
                    )
                }
            }
        }
    }

    override fun onRefresh() {
        viewModel.setEvent(MusicListContract.MusicListEvent.SwipeRefresh)
        binding.swipeRefresh.isRefreshing = false
    }
}