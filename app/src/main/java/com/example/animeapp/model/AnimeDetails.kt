package com.example.animeapp.model

// model/data/AnimeDetails.kt

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeDetailsResponse(
    @SerializedName("data") val data: AnimeDetails? = null
) : Parcelable

@Parcelize
data class AnimeDetails(
    @SerializedName("mal_id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("episodes") val episodes: Int? = 0,
    @SerializedName("score") val score: Double = 0.0,
    @SerializedName("synopsis") val synopsis: String = "",
    @SerializedName("images") val images: Images? = null,
    @SerializedName("trailer") val trailer: Trailer? = null,
    @SerializedName("genres") val genres: List<Genre> = emptyList(),
    @SerializedName("studios") val studios: List<Studio> = emptyList(),
    @SerializedName("duration") val duration: String = "",
    @SerializedName("rating") val rating: String = "",
    @SerializedName("status") val status: String = ""
) : Parcelable

@Parcelize
data class Genre(
    @SerializedName("mal_id") val id: Int = 0,
    @SerializedName("name") val name: String = ""
) : Parcelable

@Parcelize
data class Studio(
    @SerializedName("mal_id") val id: Int = 0,
    @SerializedName("name") val name: String = ""
) : Parcelable