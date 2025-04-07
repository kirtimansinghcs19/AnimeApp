package com.example.animeapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.databinding.ItemAnimeBinding
import com.example.animeapp.model.Anime

class AnimeAdapter(
    private val animeList: MutableList<Anime> = mutableListOf(),
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    fun updateData(newList: List<Anime>) {
        animeList.clear()
        animeList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemAnimeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int = animeList.size

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener(animeList[position].id)
                }
            }
        }

        fun bind(anime: Anime) {
            binding.apply {
                tvTitle.text = anime.title
                tvEpisodes.text = "Episodes: ${anime.episodes ?: "N/A"}"
                tvScore.text = "Rating: ${anime.score}"

                // Load image
                Glide.with(ivPoster.context)
                    .load(anime.images?.jpg?.imageUrl)
                    .centerCrop()
                    .into(ivPoster)
            }
        }
    }
}