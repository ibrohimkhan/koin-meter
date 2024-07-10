package com.kodeco.koinmeter

import android.app.Application
import com.kodeco.koinmeter.di.coinMarketChartModule
import com.kodeco.koinmeter.di.dataStorePrefsModule
import com.kodeco.koinmeter.di.databaseModule
import com.kodeco.koinmeter.di.favoriteCoinsModule
import com.kodeco.koinmeter.di.networkingModule
import com.kodeco.koinmeter.di.timeFrameSettingsModule
import com.kodeco.koinmeter.di.topCoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinMeterApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinMeterApplication)
            modules(
                networkingModule,
                databaseModule,
                dataStorePrefsModule,
                topCoinModule,
                coinMarketChartModule,
                favoriteCoinsModule,
                timeFrameSettingsModule,
            )
        }
    }
}
