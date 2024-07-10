package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSource
import com.kodeco.koinmeter.data.mapper.toCoinMarketChartEntity
import com.kodeco.koinmeter.data.mapper.toCoinMarketChartPrice
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository

class CoinMarketChartRepositoryImpl(
    private val remoteDataSource: CoinMarketChartRemoteDataSource,
    private val localDataSource: CoinMarketChartLocalDataSource,
) : CoinMarketChartRepository {

    private val cachedCoinMarketChartPrices = mutableMapOf<String, List<CoinMarketChartPrice>>()

    override suspend fun getCoinMarketChartData(coinId: String, days: Int): List<CoinMarketChartPrice> {
        val response = remoteDataSource.getCoinMarketChartData(coinId, days)

        if (response.isSuccessful) {
            response.body()?.let { coinMarketChartPrices ->
                val entity = coinMarketChartPrices.toCoinMarketChartEntity(coinId)

                localDataSource.insertCoinMarketChart(entity)
                cachedCoinMarketChartPrices[coinId] = coinMarketChartPrices
            }
        }

        localDataSource.getCoinMarketChartByCoinId(coinId)?.let { entity ->
            return entity.toCoinMarketChartPrice()
        }

        return cachedCoinMarketChartPrices[coinId] ?: emptyList()
    }

    override suspend fun deleteAllCoinMarketCharts() {
        localDataSource.deleteAllCoinMarketCharts()
        cachedCoinMarketChartPrices.clear()
    }
}
