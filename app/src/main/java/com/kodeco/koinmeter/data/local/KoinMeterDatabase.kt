package com.kodeco.koinmeter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kodeco.koinmeter.data.local.dao.CoinDao
import com.kodeco.koinmeter.data.local.dao.CoinMarketChartDao
import com.kodeco.koinmeter.data.local.dao.FavoriteCoinDao
import com.kodeco.koinmeter.data.local.entities.CoinEntity
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import com.kodeco.koinmeter.data.local.entities.FavoriteCoinEntity
import com.kodeco.koinmeter.data.local.typeconverter.KoinTypeConverters


const val DATABASE_VERSION = 1

@Database(
    entities = [CoinEntity::class, CoinMarketChartEntity::class, FavoriteCoinEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(KoinTypeConverters::class)
abstract class KoinMeterDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
    abstract fun coinMarketChartDao(): CoinMarketChartDao
    abstract fun favoriteCoinDao(): FavoriteCoinDao
}
