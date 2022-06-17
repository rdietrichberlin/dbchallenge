package com.deutschebahn.challange.ui.usecase

import android.content.Context

interface OpenDetailUseCase {
    suspend operator fun invoke(id: Long)
}

internal class OpenDetailUseCaseImpl(
): OpenDetailUseCase {
    override suspend fun invoke(id: Long) {
        // intentionally left empty
    }

}