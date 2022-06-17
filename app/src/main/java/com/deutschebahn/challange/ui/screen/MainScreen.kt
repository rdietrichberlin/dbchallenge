package com.deutschebahn.challange.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.deutschebahn.challange.R
import com.deutschebahn.challange.ui.components.NavPath
import com.deutschebahn.challange.ui.state.ErrorItem
import com.deutschebahn.challange.ui.state.LoadingItem
import com.deutschebahn.challange.ui.state.LoadingView
import com.deutschebahn.movieapi.model.Movie
import kotlinx.coroutines.flow.Flow

@Composable
fun MainScreen(
    router: (String) -> Unit,
    mainViewModel: MainViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MainTabToolbar(onSearchBarClick = {
            router(NavPath.SearchView.route)
        })

        MovieList(
            movies = mainViewModel.movies,
            onItemClick = {
                router("${NavPath.MovieDetail.route}?id=$it")
                mainViewModel.onDetail(it)
            }
        )
    }

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = stringResource(id = R.string.list_title)) }
//            )
//        },
//        content = {
//            MovieList(
//                movies = mainViewModel.movies,
//                onItemClick = {
//                    router("${NavPath.MovieDetail.route}?id=$it")
//                    mainViewModel.onDetail(it)
//                }
//            )
//        }
//    )
}

@Composable
fun MainTabToolbar(onSearchBarClick: () -> Unit) {
    TopAppBar(title = { Text(stringResource(id = R.string.list_title)) }, actions = {
        IconButton(
            modifier = Modifier,
            onClick = { onSearchBarClick() }) {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search_title)
            )
        }
    })
}

@Composable
fun MovieList(
    movies: Flow<PagingData<Movie>>,
    onItemClick: (id: Long) -> Unit
) {
    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyMovieItems) { movie ->
            MovieItem(
                movie = movie!!,
                onItemClick = onItemClick
            )
        }

        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onItemClick: (id: Long) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .selectable(
                false,
                onClick = {
                    onItemClick(movie.id)
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieTitle(
            movie.title,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}