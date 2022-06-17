package com.deutschebahn.challange.repository

import com.deutschebahn.movieapi.model.Movie
import com.deutschebahn.movieapi.model.MovieResult

interface MovieRepository {
    suspend fun nowPlaying(
        page: Int,
    ): MovieResult

    suspend fun details(
        id: Long
    ): Movie

    suspend fun search(
        query: String,
        page: Int = 1,
    ): MovieResult
}

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun nowPlaying(page: Int) =
        remoteDataSource.nowPlaying(page).fold(
            onFailure = {
                MovieResult.Empty()
            },
            onSuccess = {
                it
            }
        )

    override suspend fun details(id: Long) =
        remoteDataSource.details(id).fold(
            onFailure = {
                Movie.Empty()
            },
            onSuccess = {
                it
            }
        )
    override suspend fun search(query: String, page: Int) =
        remoteDataSource.search(query, page).fold(
            onFailure = {
                MovieResult.Empty()
            },
            onSuccess = {
                it
            }
        )
}