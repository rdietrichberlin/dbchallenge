package com.deutschebahn.challange.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deutschebahn.challange.repository.MovieRepository
import com.deutschebahn.movieapi.model.MovieResult
import com.deutschebahn.movieapi.model.MovieResult.Companion.Empty
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MovieSearchViewModel : ViewModel() {
    abstract val state: Flow<StateData>

    data class StateData(
        val searchText: String = "",
        val movies: MovieResult = MovieResult.Empty(),
        val showProgressBar: Boolean = false
    ) {
        companion object {
            fun Empty()  = StateData()
        }
    }

    abstract fun onSearchTextChanged(changedSearchText: String)
    abstract fun onClearClick()
}

internal class MovieSearchViewModelImpl(
    private val movieRepository: MovieRepository,
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : MovieSearchViewModel() {

    private val searchText: MutableStateFlow<String> = MutableStateFlow("")
    private var showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var matched: MutableStateFlow<MovieResult> = MutableStateFlow(Empty())

    override val state = combine(
        searchText,
        matched,
        showProgressBar
    ) { text, matchedUsers, showProgress ->
        StateData(
            text,
            matchedUsers,
            showProgress
        )
    }

    override fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matched.value = Empty()
            return
        } else {
            viewModelScope.launch(dispatcherIo) {
                Log.d("VM", "Search >>$changedSearchText<<")
                matched.value = movieRepository.search(changedSearchText)
            }
        }
    }

    override fun onClearClick() {
        searchText.value = ""
        matched.value = Empty()
    }

}