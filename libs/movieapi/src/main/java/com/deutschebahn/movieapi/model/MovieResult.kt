package com.deutschebahn.movieapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieResult(
    val page: Int,
    val results: List<Movie>,
    val dates: Dates,
    @SerializedName("total_pages") val totalPpages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    companion object {
        fun Empty() = MovieResult(
            page = 0,
            results = emptyList(),
            dates = Dates(null, null),
            totalPpages = 0,
            totalResults = 0
        )

        fun Error() = MovieResult(
            page = 0,
            results = emptyList(),
            dates = Dates(null, null),
            totalPpages = 0,
            totalResults = 0
        )
    }
}