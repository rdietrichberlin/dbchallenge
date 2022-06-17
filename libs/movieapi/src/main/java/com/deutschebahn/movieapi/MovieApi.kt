package com.deutschebahn.movieapi

import com.deutschebahn.movieapi.internal.MovieApiImpl
import com.deutschebahn.movieapi.model.ApiResponse
import com.deutschebahn.movieapi.model.Movie
import com.deutschebahn.movieapi.model.MovieResult

interface MovieApi {
    suspend fun nowPlaying(
        page: Int = 1,
        language: String? = null,
    ): ApiResponse<MovieResult>

    suspend fun details(
        id: Long,
        language: String? = null,
    ): ApiResponse<Movie>

    suspend fun search(
        query: String,
        page: Int = 1,
        language: String? = null,
    ): ApiResponse<MovieResult>

    companion object {
        fun create(config: MovieApiConfig): MovieApi = MovieApiImpl(config)
    }
}