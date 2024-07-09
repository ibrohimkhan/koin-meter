package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSource
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import com.kodeco.koinmeter.domain.model.Coin

class TopCoinsRepositoryImpl(
    private val remoteDataSource: TopCoinsRemoteDataSource,
    private val localDataSource: TopCoinsLocalDataSource,
) : TopCoinsRepository {

    private var topCoins: List<Coin> = emptyList()

    override suspend fun getTopCoins(timeframe: String): List<Coin> {
        val coinList = remoteDataSource.getTopCoins(timeframe)
        topCoins = coinList

        return coinList
    }

    override suspend fun getCoin(coinId: String): Coin? =
        topCoins.find { it.id == coinId }
}
