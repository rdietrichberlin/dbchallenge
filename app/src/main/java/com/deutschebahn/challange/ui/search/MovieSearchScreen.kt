package com.deutschebahn.challange.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import com.deutschebahn.challange.R
import com.deutschebahn.challange.ui.components.SearchBarUI
import com.deutschebahn.challange.ui.components.rememberFlowWithLifecycle
import com.deutschebahn.challange.ui.screen.MovieItem
import com.deutschebahn.movieapi.model.MovieResult

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun MovieSearchScreen(
    onBack: () -> Unit,
    movieSearchViewModel: MovieSearchViewModel
) {
    val movieSearchViewModelState by rememberFlowWithLifecycle(movieSearchViewModel.state)
        .collectAsState(initial = MovieSearchViewModel.StateData.Empty())
    SearchBarUI(
        searchText = movieSearchViewModelState.searchText,
        placeholderText = stringResource(id = R.string.search_title),
        onSearchTextChanged = { movieSearchViewModel.onSearchTextChanged(it) },
        onClearClick = { movieSearchViewModel.onClearClick() },
        onNavigateBack = {
            onBack()
        },
        matchesFound = movieSearchViewModelState.movies.totalResults != 0
    ) {
        Movies(movies = movieSearchViewModelState.movies) {
            // TBD navigate to detail
        }
    }
}

@Composable
fun Movies(
    movies: MovieResult,
    onClick: (Long) -> Unit
) {
    movies.results.forEach { movie ->
        MovieItem(movie = movie) {
            onClick(it)
        }
        Divider()
    }
}