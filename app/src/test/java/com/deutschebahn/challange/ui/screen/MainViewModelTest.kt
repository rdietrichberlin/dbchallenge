package com.deutschebahn.challange.ui.screen

import com.deutschebahn.challange.repository.MovieRepository
import com.deutschebahn.challange.ui.usecase.OpenDetailUseCase
import com.deutschebahn.test.TestCoroutineRule
import io.mockk.MockKSettings.relaxed
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

internal class MainViewModelTest {

    @get:Rule
    val test = TestCoroutineRule()

    private val movie = mockk<MovieRepository>(relaxed = true)
    private val openDetailUseCase = mockk<OpenDetailUseCase>(relaxed = true)

    private val sut by lazy {
        MainViewModelImpl(
            movie,
            openDetailUseCase
        )
    }

    @Test
    fun `routes to detail`() {
        sut.onDetail(1)

        coVerify {
            openDetailUseCase.invoke(1)
        }
    }
}