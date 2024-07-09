package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSource
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository

class CoinMarketChartRepositoryImpl(
    private val remoteDataSource: CoinMarketChartRemoteDataSource,
    private val localDataSource: CoinMarketChartLocalDataSource,
) : CoinMarketChartRepository {

    override suspend fun getCoinMarketChartData(coinId: String, days: Int) =
        remoteDataSource.getCoinMarketChartData(coinId, days)
}
