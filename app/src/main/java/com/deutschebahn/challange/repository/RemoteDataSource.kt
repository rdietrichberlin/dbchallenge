package com.deutschebahn.challange.repository

import com.deutschebahn.movieapi.MovieApi
import com.deutschebahn.movieapi.model.ApiResponse
import com.deutschebahn.movieapi.model.Movie
import com.deutschebahn.movieapi.model.MovieResult

interface RemoteDataSource {
    suspend fun nowPlaying(
        page: Int = 1,
        language: String? = null,
        apiKey: String? = null
    ): ApiResponse<MovieResult>

    suspend fun details(
        id: Long,
        language: String? = null,
        apiKey: String? = null
    ): ApiResponse<Movie>

    suspend fun search(
        query: String,
        page: Int = 1,
        language: String? = null,
        apiKey: String? = null
    ): ApiResponse<MovieResult>
}

internal class RemoteDataSourceImpl(
    private val movieApi: MovieApi
) : RemoteDataSource {
    override suspend fun nowPlaying(
        page: Int,
        language: String?,
        apiKey: String?
    ) = movieApi.nowPlaying(page, language)

    override suspend fun details(
        id: Long,
        language: String?,
        apiKey: String?
    ) = movieApi.details(id, language)

    override suspend fun search(
        query: String,
        page: Int,
        language: String?,
        apiKey: String?
    ) = movieApi.search(query, page, language)
}