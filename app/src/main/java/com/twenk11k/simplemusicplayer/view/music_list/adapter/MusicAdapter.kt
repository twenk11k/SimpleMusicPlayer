package com.twenk11k.simplemusicplayer.view.music_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.twenk11k.simplemusicplayer.databinding.ItemMusicBinding
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

class MusicAdapter(val imageLoader: ImageLoader, private val listener: OnItemSelectedListener) :
    ListAdapter<MusicDomainModel, MusicAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMusicBinding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                listener.navigateToMusicPlay(getItem(absoluteAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MusicDomainModel) {
            binding.run {
                txtTitle.text = item.title
                with(imgCover) {
                    load(item.cover)
                }
                handleFavorite(item)
            }
        }

        /**
         * Select ImageView based on isFavorite Boolean value of MusicDomainModel
         * When ImageView is clicked ->
         * If ImageView is 'selected' clear favorite database then add the selected item
         * If ImageView is 'not selected' clear favorite database
         * */
        private fun handleFavorite(item: MusicDomainModel) {
            binding.imgFavorite.run {
                this.isSelected = item.isFavorite
                this.setOnClickListener {
                    if (!this.isSelected) {
                        listener.setFavorite(item.title)
                    } else {
                        listener.clearFavorites()
                    }
                    this.isSelected = !this.isSelected
                }
            }
        }
    }
}

private val DIFF_UTIL = object : DiffUtil.ItemCallback<MusicDomainModel>() {
    override fun areItemsTheSame(
        oldItem: MusicDomainModel,
        newItem: MusicDomainModel
    ) = oldItem.title == newItem.title && oldItem.isFavorite == newItem.isFavorite

    override fun areContentsTheSame(
        oldItem: MusicDomainModel,
        newItem: MusicDomainModel
    ) =
        oldItem == newItem
}
