package com.kodeco.koinmeter.data.remote.datasource.topcoins

import com.kodeco.koinmeter.data.remote.RemoteApiService
import com.kodeco.koinmeter.domain.model.Coin

class TopCoinsRemoteDataSourceImpl(
    private val apiService: RemoteApiService
) : TopCoinsRemoteDataSource {

    override suspend fun getTopCoins(timeframe: String): List<Coin> =
        apiService.getTopCoins(percentageTimeframe = timeframe).body() ?: emptyList()
}
