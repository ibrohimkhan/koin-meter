package com.kodeco.koinmeter.data.local.datasource.coinmarketchart

import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import kotlinx.coroutines.flow.Flow

interface CoinMarketChartLocalDataSource {
    fun getAllCoinMarketCharts(): Flow<List<CoinMarketChartEntity>>

    suspend fun getCoinMarketChartByCoinId(coinId: String): CoinMarketChartEntity?

    suspend fun insertCoinMarketCharts(coinMarketCharts: List<CoinMarketChartEntity>)

    suspend fun insertCoinMarketChart(coinMarketChart: CoinMarketChartEntity)

    suspend fun deleteAllCoinMarketCharts()
}
