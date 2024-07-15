package com.kodeco.koinmeter.domain.usecase.coinmarketchart

import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository

class GetCoinMarketChartUseCase(
    private val repository: CoinMarketChartRepository
) {
    suspend operator fun invoke(coinId: String, days: Int): List<CoinMarketChartPrice> =
        repository.getCoinMarketChartData(coinId, days)
}
