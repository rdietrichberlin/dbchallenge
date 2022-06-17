package com.deutschebahn.challange.ui.screen

import com.deutschebahn.challange.repository.MovieRepository
import com.deutschebahn.challange.ui.search.MovieSearchViewModel
import com.deutschebahn.challange.ui.search.MovieSearchViewModelImpl
import com.deutschebahn.challange.ui.usecase.OpenDetailUseCase
import com.deutschebahn.test.TestCoroutineRule
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull

internal class MovieSearchViewModelTest {

    @get:Rule
    val test = TestCoroutineRule()

    private val movie = mockk<MovieRepository>(relaxed = true)

    private val sut by lazy {
        MovieSearchViewModelImpl(
            movie
        )
    }

    @Test
    fun `has initial state`() {
        // TBD
        assertNotNull(sut.state)
    }
}