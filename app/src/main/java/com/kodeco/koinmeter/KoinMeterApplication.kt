package com.kodeco.koinmeter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinMeterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinMeterApplication)
            modules(com.kodeco.koinmeter.data.remote.networkingModule)
        }
    }
}