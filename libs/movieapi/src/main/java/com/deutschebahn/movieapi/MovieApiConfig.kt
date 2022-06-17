package com.deutschebahn.movieapi

import okhttp3.Interceptor

data class MovieApiConfig(
    val baseUrl: String = "https://api.themoviedb.org/3/",
    val readTimeoutMs: Long = DEFAULT_TIMEOUT_MS,
    val writeTimeoutMs: Long = DEFAULT_TIMEOUT_MS,
    val connectTimeoutMs: Long = DEFAULT_TIMEOUT_MS,
    val apiKey: String? = "14e04198237394f33fda689f91203eea",
    val language: String = "de",
    val includeAdult: Boolean = true,
    val retryOnConnectionFailure: Boolean = true,
    val additionalInterceptors: List<Interceptor> = emptyList()
) {
    companion object {
        const val DEFAULT_TIMEOUT_MS = 10 * 1000L
    }
}
