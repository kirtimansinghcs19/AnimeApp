package com.example.animeapp.model.AnimeRepository

import com.example.animeapp.model.Anime
import com.example.animeapp.model.AnimeDetails
import com.example.animeapp.model.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository {
    private val api = ApiClient.jikanApi

    suspend fun getTopAnime(): List<Anime> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getTopAnime()
                response.data
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    suspend fun getAnimeDetails(id: Int): AnimeDetails? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAnimeDetails(id)
                response.data
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}