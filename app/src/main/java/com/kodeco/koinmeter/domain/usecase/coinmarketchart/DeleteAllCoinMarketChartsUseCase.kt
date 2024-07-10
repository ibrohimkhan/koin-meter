package com.kodeco.koinmeter.domain.usecase.coinmarketchart

import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository

class DeleteAllCoinMarketChartsUseCase(
    private val repository: CoinMarketChartRepository
) {
    suspend operator fun invoke() = repository.deleteAllCoinMarketCharts()
}
