package com.kodeco.koinmeter.data.local.datasource.coinmarketchart

import com.kodeco.koinmeter.data.local.dao.CoinMarketChartDao
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import kotlinx.coroutines.flow.Flow

class CoinMarketChartLocalDataSourceImpl(
    private val coinMarketChartDao: CoinMarketChartDao
) : CoinMarketChartLocalDataSource {

    override fun getAllCoinMarketCharts(): Flow<List<CoinMarketChartEntity>> =
        coinMarketChartDao.getAllCoinMarketCharts()

    override suspend fun getCoinMarketChartByCoinId(coinId: String): CoinMarketChartEntity? =
        coinMarketChartDao.getCoinMarketChartByCoinId(coinId)

    override suspend fun insertCoinMarketCharts(coinMarketCharts: List<CoinMarketChartEntity>) =
        coinMarketChartDao.insertCoinMarketCharts(coinMarketCharts)

    override suspend fun insertCoinMarketChart(coinMarketChart: CoinMarketChartEntity) =
        coinMarketChartDao.insertCoinMarketChart(coinMarketChart)

    override suspend fun deleteAllCoinMarketCharts() =
        coinMarketChartDao.deleteAllCoinMarketCharts()
}
