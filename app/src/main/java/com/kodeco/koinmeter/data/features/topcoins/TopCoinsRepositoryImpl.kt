package com.kodeco.koinmeter.data.features.topcoins

import com.kodeco.koinmeter.data.features.topcoins.remote.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.domain.features.topcoins.TopCoinsRepository
import com.kodeco.koinmeter.domain.model.Coin

class TopCoinsRepositoryImpl(
    private val remoteDataSource: TopCoinsRemoteDataSource,
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
