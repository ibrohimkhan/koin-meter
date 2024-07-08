package com.kodeco.koinmeter.data.features.topcoins.remote

import com.kodeco.koinmeter.data.network.RemoteApiService
import com.kodeco.koinmeter.domain.model.Coin

class TopCoinsRemoteDataSourceImpl(
    private val apiService: RemoteApiService
) : TopCoinsRemoteDataSource {

    override suspend fun getTopCoins(timeframe: String): List<Coin> {
        val response = apiService.getTopCoins(percentageTimeframe = timeframe)
        return response.body() ?: emptyList()
    }
}
