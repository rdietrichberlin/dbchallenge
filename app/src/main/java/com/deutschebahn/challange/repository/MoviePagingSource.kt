package com.deutschebahn.challange.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.deutschebahn.movieapi.model.Movie
import com.deutschebahn.movieapi.model.MovieResult
import java.io.IOException

internal class MoviePagingSource(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPage = params.key ?: 1
        return when (val movieListResponse = movieRepository.nowPlaying(nextPage)) {
            MovieResult.Error() -> LoadResult.Error(IOException("error"))
            else -> LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page.plus(1)
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.pages.last().nextKey
}