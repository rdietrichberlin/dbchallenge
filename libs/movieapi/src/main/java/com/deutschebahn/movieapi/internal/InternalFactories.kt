package com.deutschebahn.movieapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal fun createRetrofit(
    config: MovieApiConfig,
    okHttpClient: OkHttpClient = createOkHttpClient(config),
    converterFactories: List<Converter.Factory> = listOf(GsonConverterFactory.create(createGson()))
): Retrofit = Retrofit.Builder()
    .baseUrl(config.baseUrl)
    .client(okHttpClient)
    .apply { converterFactories.forEach { addConverterFactory(it) } }
    .build()

internal fun createOkHttpClient(
    config: MovieApiConfig,
    additionalInterceptors: List<Interceptor> = emptyList()
) =
    OkHttpClient.Builder()
        .readTimeout(config.readTimeoutMs, TimeUnit.MILLISECONDS)
        .writeTimeout(config.writeTimeoutMs, TimeUnit.MILLISECONDS)
        .connectTimeout(config.connectTimeoutMs, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(config.retryOnConnectionFailure)
        .also { builder ->
            additionalInterceptors.forEach {  interceptor ->
                builder.addInterceptor(interceptor)
            }
        }
        .build()


internal fun createGson(): Gson = GsonBuilder()
    .create()
