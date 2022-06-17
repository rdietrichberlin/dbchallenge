package com.deutschebahn.movieapi.internal

import com.deutschebahn.movieapi.model.Movie
import com.deutschebahn.movieapi.model.MovieResult
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @GET("movie/now_playing")
    suspend fun nowPlaying(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String?
    ): Response<MovieResult>

    @GET("movie/{id}")
    suspend fun details(
        @Path("id") id: Long,
        @Query("language") language: String,
        @Query("api_key") apiKey: String?
    ): Response<Movie>

    @GET("search/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String?,
        @Query("include_adult") includeAdult: Boolean
    ): Response<MovieResult>
}