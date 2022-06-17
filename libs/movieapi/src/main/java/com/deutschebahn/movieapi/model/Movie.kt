package com.deutschebahn.movieapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Movie(
    val id: Long,
    val title: String,
    @SerializedName("backdrop_path") val backdropPath: String?,

    @SerializedName("poster_path") val posterPth: String,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("original_language") val originalLanguage: String,
    val popularity: String? = null,
    @SerializedName("vote_count") val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: String,
) {
    companion object {
        fun Empty() = Movie(
            id = -1,
            title = "",
            backdropPath = "",
            posterPth = "",
            adult = false,
            overview = "",
            releaseDate = "",
            originalTitle = "",
            originalLanguage = "",
            voteCount = 0,
            video = false,
            voteAverage = ""
            )
    }
}