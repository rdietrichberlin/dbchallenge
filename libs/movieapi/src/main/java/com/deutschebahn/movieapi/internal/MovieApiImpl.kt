package com.deutschebahn.movieapi.internal

import com.deutschebahn.movieapi.MovieApi
import com.deutschebahn.movieapi.MovieApiConfig
import com.deutschebahn.movieapi.createOkHttpClient
import com.deutschebahn.movieapi.createRetrofit
import com.deutschebahn.movieapi.model.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.net.HttpURLConnection

internal class MovieApiImpl(
    private val config: MovieApiConfig
): MovieApi {

    private val retrofit: Retrofit = createRetrofit(
        config = config,
        okHttpClient = createOkHttpClient(
            config,
            listOf(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        )
    )

    private val serviceDelegate:MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    override suspend fun nowPlaying(
        page: Int,
        language: String?,
    ): ApiResponse<MovieResult> =
        safeCall(
            failureHandler = ::mapStatusCodeToFailure,
            exceptionHandler = ::mapExceptionToFailure,
            transform = { it!! }
        ) {
            serviceDelegate.nowPlaying(
                page = page,
                language = language ?: config.language,
                apiKey = config.apiKey
            )
        }

    override suspend fun details(
        id: Long,
        language: String?,
    ): ApiResponse<Movie> =
        safeCall(
            failureHandler = ::mapStatusCodeToFailure,
            exceptionHandler = ::mapExceptionToFailure,
            transform = { it!! }
        ) {
            serviceDelegate.details(
                id = id,
                language = language ?: config.language,
                apiKey = config.apiKey
            )
        }

    override suspend fun search(
        query: String,
        page: Int,
        language: String?,
    ): ApiResponse<MovieResult> =
        safeCall(
            failureHandler = ::mapStatusCodeToFailure,
            exceptionHandler = ::mapExceptionToFailure,
            transform = { it!! }
        ) {
            serviceDelegate.search(
                query = query,
                page = page,
                language = language ?: config.language,
                apiKey = config.apiKey,
                includeAdult = config.includeAdult
            )
        }
}

internal fun mapStatusCodeToFailure(
    statusCode: Int,
    message: String
): Failure {
    return when(statusCode) {
        HttpURLConnection.HTTP_OK -> Failure.None
        else -> UnknownError(statusCode,message)
    }
}

internal fun mapExceptionToFailure(exception: Throwable):Failure =
    UnexpectedFailure(exception.message ?: "")