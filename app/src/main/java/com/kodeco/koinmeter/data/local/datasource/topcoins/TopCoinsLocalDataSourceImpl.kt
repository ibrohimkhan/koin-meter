package com.kodeco.koinmeter.data.local.datasource.topcoins

import com.kodeco.koinmeter.data.local.dao.CoinDao
import com.kodeco.koinmeter.data.local.entities.CoinEntity
import kotlinx.coroutines.flow.Flow

class TopCoinsLocalDataSourceImpl(
    private val coinDao: CoinDao
) : TopCoinsLocalDataSource {

    override fun getAllCoins(): Flow<List<CoinEntity>> =
        coinDao.getAllCoins()

    override suspend fun getCoinById(coinId: String): CoinEntity? =
        coinDao.getCoinById(coinId)

    override suspend fun deleteAllCoins() =
        coinDao.deleteAllCoins()

    override suspend fun insertCoins(coins: List<CoinEntity>) =
        coinDao.insertCoins(coins)

    override suspend fun insertCoin(coin: CoinEntity) =
        coinDao.insertCoin(coin)
}
