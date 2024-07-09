package com.kodeco.koinmeter.data.remote.datasource.coinmarketchart

import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice

interface CoinMarketChartRemoteDataSource {
    suspend fun getCoinMarketChartData(coinId: String, days: Int): List<CoinMarketChartPrice>
}
