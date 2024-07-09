package com.kodeco.koinmeter.data.local

import android.content.Context
import androidx.room.Room
import com.kodeco.koinmeter.data.local.dao.CoinDao
import com.kodeco.koinmeter.data.local.dao.CoinMarketChartDao

const val DATABASE_NAME = "koin_meter_database"

fun provideDatabase(context: Context): KoinMeterDatabase = Room
    .databaseBuilder(
        context,
        KoinMeterDatabase::class.java,
        DATABASE_NAME
    )
    .fallbackToDestructiveMigration()
    .build()

fun provideCoinDao(database: KoinMeterDatabase): CoinDao =
    database.coinDao()

fun provideCoinMarketChartDao(database: KoinMeterDatabase): CoinMarketChartDao =
    database.coinMarketChartDao()
