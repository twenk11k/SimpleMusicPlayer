package com.twenk11k.simplemusicplayer.view.music_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.twenk11k.simplemusicplayer.databinding.ItemMusicBinding
import com.twenk11k.simplemusicplayer.domain.model.MusicDomainModel

class MusicAdapter(val imageLoader: ImageLoader) : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    private var items = ArrayList<MusicDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMusicBinding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateAdapter(list: List<MusicDomainModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
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
