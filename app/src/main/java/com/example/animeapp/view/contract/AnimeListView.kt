package com.example.animeapp.view.contract

import com.example.animeapp.model.Anime

interface AnimeListView {
    fun showLoading()
    fun hideLoading()
    fun showAnimeList(animeList: List<Anime>)
    fun showError(message: String)
    fun navigateToDetail(animeId: Int)
}