package com.deutschebahn.challange.di

import com.deutschebahn.challange.ui.usecase.OpenDetailUseCase
import com.deutschebahn.challange.ui.usecase.OpenDetailUseCaseImpl
import org.koin.dsl.module

internal val useCaseModule = module {

    single<OpenDetailUseCase> { OpenDetailUseCaseImpl() }
}
