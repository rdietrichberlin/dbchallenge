package com.deutschebahn.challange.di

import com.deutschebahn.challange.ui.detail.MovieDetailViewModel
import com.deutschebahn.challange.ui.detail.MovieDetailViewModelImpl
import com.deutschebahn.challange.ui.screen.MainViewModel
import com.deutschebahn.challange.ui.screen.MainViewModelImpl
import com.deutschebahn.challange.ui.search.MovieSearchViewModel
import com.deutschebahn.challange.ui.search.MovieSearchViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel<MainViewModel> { MainViewModelImpl(get(), get()) }

    viewModel<MovieDetailViewModel> { MovieDetailViewModelImpl(get()) }

    viewModel<MovieSearchViewModel> { MovieSearchViewModelImpl(get()) }
}