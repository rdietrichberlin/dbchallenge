package com.deutschebahn.challange.di

import com.deutschebahn.challange.repository.MovieRepository
import com.deutschebahn.challange.repository.MovieRepositoryImpl
import com.deutschebahn.challange.repository.RemoteDataSource
import com.deutschebahn.challange.repository.RemoteDataSourceImpl
import com.deutschebahn.movieapi.MovieApi
import com.deutschebahn.movieapi.MovieApiConfig
import org.koin.dsl.module

internal val storageModule = module {

    single {
        MovieApi.create(MovieApiConfig())
    }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
}
