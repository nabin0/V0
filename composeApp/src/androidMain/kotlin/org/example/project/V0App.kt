package org.example.project

import android.app.Application
import di.androidAppModule
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class V0App: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@V0App)
            modules(androidAppModule)
        }
    }
}