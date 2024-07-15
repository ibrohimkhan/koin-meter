package com.kodeco.koinmeter.data.remote.datasource.coinmarketchart

import com.kodeco.koinmeter.data.remote.RemoteApiService

class CoinMarketChartRemoteDataSourceImpl(
    private val apiService: RemoteApiService
) : CoinMarketChartRemoteDataSource {

    override suspend fun getCoinMarketChartData(coinId: String, days: Int) =
        apiService.getCoinMarketChartData(coinId = coinId, days = days)
}
