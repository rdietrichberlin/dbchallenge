package com.deutschebahn.challange.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.deutschebahn.challange.repository.MovieRepository
import com.deutschebahn.challange.repository.MoviePagingSource
import com.deutschebahn.challange.ui.usecase.OpenDetailUseCase
import com.deutschebahn.movieapi.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class MainViewModel: ViewModel() {
    abstract val movies: Flow<PagingData<Movie>>

    sealed class State {
        object Initial : State()
    }

    abstract fun onDetail(id: Long)
}

internal class MainViewModelImpl(
    private val movieRepository: MovieRepository,
    private val openDetailUseCase: OpenDetailUseCase,
): MainViewModel() {

    override val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 15)) {
        MoviePagingSource(movieRepository)
    }.flow

    override fun onDetail(id: Long) {
        viewModelScope.launch {
            openDetailUseCase(id)
        }
    }
}