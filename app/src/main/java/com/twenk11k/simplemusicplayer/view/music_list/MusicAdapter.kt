package com.twenk11k.simplemusicplayer.view.music_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.twenk11k.simplemusicplayer.databinding.ItemMusicBinding
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

class MusicAdapter(val imageLoader: ImageLoader) : ListAdapter<MusicDomainModel, MusicAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMusicBinding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MusicDomainModel) {
            binding.run {
                txtAudioTitle.text = item.title
                with(imgCover) {
                    load(item.cover)
                }
            }
        }
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MusicDomainModel>() {
    override fun areItemsTheSame(
        oldItem: MusicDomainModel,
        newItem: MusicDomainModel
    ) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: MusicDomainModel,
        newItem: MusicDomainModel
    ) =
        oldItem == newItem
}