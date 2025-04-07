package com.example.animeapp.model.api

import com.example.animeapp.model.AnimeDetailsResponse
import com.example.animeapp.model.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApi {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailsResponse
}
