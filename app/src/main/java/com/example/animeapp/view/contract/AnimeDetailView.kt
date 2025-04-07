package com.example.animeapp.view.contract

import com.example.animeapp.model.AnimeDetails


interface AnimeDetailView {
    fun showLoading()
    fun hideLoading()
    fun showAnimeDetails(animeDetails: AnimeDetails)
    fun showError(message: String)
    fun playTrailer(youtubeId: String)
    fun showPoster(imageUrl: String)
}