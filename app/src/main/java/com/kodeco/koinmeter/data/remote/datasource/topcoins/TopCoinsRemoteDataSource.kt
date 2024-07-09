package com.kodeco.koinmeter.data.remote.datasource.topcoins

import com.kodeco.koinmeter.domain.model.Coin

interface TopCoinsRemoteDataSource {
    suspend fun getTopCoins(timeframe: String): List<Coin>
}
