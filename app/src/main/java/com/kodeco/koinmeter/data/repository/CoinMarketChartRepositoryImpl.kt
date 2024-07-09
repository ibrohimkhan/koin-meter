package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository

class CoinMarketChartRepositoryImpl(
    private val remoteDataSource: CoinMarketChartRemoteDataSource,
) : CoinMarketChartRepository {

    override suspend fun getCoinMarketChartData(coinId: String, days: Int) =
        remoteDataSource.getCoinMarketChartData(coinId, days)
}
