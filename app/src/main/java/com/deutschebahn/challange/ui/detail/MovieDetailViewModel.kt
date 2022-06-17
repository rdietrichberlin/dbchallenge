package com.deutschebahn.challange.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deutschebahn.challange.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class MovieDetailViewModel : ViewModel() {
    abstract  val state: StateFlow<State>

    sealed class State {
        object Initial : State()
        data class Data(
            val id: Long,
            val title: String,
            val vote: String,
            val overview: String,
            val poster: String
        ) : State()
    }

    abstract fun start(id: Long)
}

internal class MovieDetailViewModelImpl(
    private val movieRepository: MovieRepository,
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : MovieDetailViewModel() {

    private val _state = MutableStateFlow<State>(State.Initial)
    override val state: StateFlow<State> = _state

    override fun start(id: Long) {
        viewModelScope.launch(dispatcherIo) {
            val details = movie(id)
            _state.value = State.Data(
                id = details.id,
                title = details.title,
                vote = details.voteAverage,
                overview = details.overview,
                poster = details.posterPth
            )
        }
    }

    private suspend fun movie(id: Long) = movieRepository.details(id)
}