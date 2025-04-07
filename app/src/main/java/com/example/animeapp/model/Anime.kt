package com.example.animeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeResponse(
    @SerializedName("data") val data: List<Anime> = emptyList(),
    @SerializedName("pagination") val pagination: Pagination? = null
) : Parcelable

@Parcelize
data class Pagination(
    @SerializedName("last_visible_page") val lastVisiblePage: Int = 0,
    @SerializedName("has_next_page") val hasNextPage: Boolean = false
) : Parcelable

@Parcelize
data class Anime(
    @SerializedName("mal_id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("episodes") val episodes: Int? = 0,
    @SerializedName("score") val score: Double = 0.0,
    @SerializedName("images") val images: Images? = null,
    @SerializedName("trailer") val trailer: Trailer? = null
) : Parcelable

@Parcelize
data class Images(
    @SerializedName("jpg") val jpg: ImageUrls? = null,
    @SerializedName("webp") val webp: ImageUrls? = null
) : Parcelable

@Parcelize
data class ImageUrls(
    @SerializedName("image_url") val imageUrl: String = "",
    @SerializedName("small_image_url") val smallImageUrl: String = "",
    @SerializedName("large_image_url") val largeImageUrl: String = ""
) : Parcelable

@Parcelize
data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("embed_url") val embedUrl: String? = null
) : Parcelable