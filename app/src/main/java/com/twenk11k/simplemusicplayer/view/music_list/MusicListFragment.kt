package com.twenk11k.simplemusicplayer.view.music_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.ImageLoader
import com.google.android.material.snackbar.Snackbar
import com.twenk11k.simplemusicplayer.databinding.FragmentMusicListBinding
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListContract.*
import com.twenk11k.simplemusicplayer.presentation.viewmodel.music_list.MusicListViewModel
import com.twenk11k.simplemusicplayer.view.util.collectWhenStarted
import com.twenk11k.simplemusicplayer.view.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnItemSelectedListener {

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
        adapter = MusicAdapter(imageLoader, this)
        binding.rvMusic.adapter = adapter
    }

    private fun observeViewState() {
        viewLifecycleOwner.collectWhenStarted(viewModel.viewState) { state ->
            render(state)
        }
    }

    private fun render(state: MusicListViewState) {
        binding.apply {
            swipeRefresh.isRefreshing = state.loading
            binding.rvMusic.visibility = View.VISIBLE
            adapter?.submitList(state.songs)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun observeViewEffect() {
        viewLifecycleOwner.collectWhenStarted(viewModel.viewEffect) {
            reactTo(it)
        }
    }

    private fun reactTo(effect: ViewEffect) {
        binding.apply {
            when (effect) {
                is ViewEffect.ShowSnackBarError -> {
                    showSnackBar(
                        effect.error,
                        Snackbar.LENGTH_SHORT
                    )
                }
            }
        }
    }

    override fun onRefresh() {
        viewModel.setEvent(MusicListEvent.SwipeRefresh)
        binding.swipeRefresh.isRefreshing = false
    }

    override fun navigateToMusicPlay(item: MusicDomainModel) {
        val action = MusicListFragmentDirections.actionMusicListFragmentToMusicPlayFragment(
            item.title,
            item.audio,
            item.cover,
            item.totalDurationMs
        )
        findNavController().navigate(action)
    }

    override fun setFavorite(title: String) {
        viewModel.setFavoriteMusic(title)
    }

    override fun clearFavorites() {
        viewModel.clearFavorites()
    }
}
