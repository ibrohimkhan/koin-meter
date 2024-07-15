package com.kodeco.koinmeter.domain.repository

import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice

interface CoinMarketChartRepository {
    suspend fun getCoinMarketChartData(coinId: String, days: Int): List<CoinMarketChartPrice>
    suspend fun deleteAllCoinMarketCharts()
}
