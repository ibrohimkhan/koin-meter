package com.kodeco.koinmeter.data.local.datasource.topcoins

import com.kodeco.koinmeter.data.local.entities.CoinEntity
import kotlinx.coroutines.flow.Flow

interface TopCoinsLocalDataSource {
    fun getAllCoins(): Flow<List<CoinEntity>>

    suspend fun getCoinById(coinId: String): CoinEntity?

    suspend fun deleteAllCoins()

    suspend fun insertCoins(coins: List<CoinEntity>)

    suspend fun insertCoin(coin: CoinEntity)
}
