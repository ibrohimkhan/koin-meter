package com.kodeco.koinmeter.data.remote.datasource.coinmarketchart

import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import retrofit2.Response

interface CoinMarketChartRemoteDataSource {
    suspend fun getCoinMarketChartData(coinId: String, days: Int): Response<List<CoinMarketChartPrice>>
}
