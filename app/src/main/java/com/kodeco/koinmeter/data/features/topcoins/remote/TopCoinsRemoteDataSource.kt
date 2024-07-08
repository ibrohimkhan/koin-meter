package com.kodeco.koinmeter.data.features.topcoins.remote

import com.kodeco.koinmeter.domain.model.Coin

interface TopCoinsRemoteDataSource {
    suspend fun getTopCoins(timeframe: String): List<Coin>
}
