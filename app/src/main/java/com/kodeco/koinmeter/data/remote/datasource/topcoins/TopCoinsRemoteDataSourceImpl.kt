package com.kodeco.koinmeter.data.remote.datasource.topcoins

import com.kodeco.koinmeter.data.remote.RemoteApiService

class TopCoinsRemoteDataSourceImpl(
    private val apiService: RemoteApiService
) : TopCoinsRemoteDataSource {

    override suspend fun getTopCoins(timeframe: String) =
        apiService.getTopCoins(percentageTimeframe = timeframe)
}
