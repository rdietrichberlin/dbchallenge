package com.deutschebahn.challange

import android.app.Application
import com.deutschebahn.challange.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChallengeApp)
            modules(
                appModules()
            )
        }
    }
}