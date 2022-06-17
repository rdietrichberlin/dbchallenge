package com.deutschebahn.challange

import android.app.Application
import com.deutschebahn.challange.di.appModules
import com.deutschebahn.challange.di.storageModule
import com.deutschebahn.challange.di.useCaseModule
import com.deutschebahn.challange.di.viewModelModule
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.check.checkModules

internal class CheckDITest {

    private val application = mockk<Application> {
        every { packageName } returns "com.test"
    }

    @Test fun `verify DI can find all definitions`() {
        try {
            startKoin {
                androidContext(application)
                appModules()
            }.checkModules()
        } finally {
            stopKoin()
        }
    }
}