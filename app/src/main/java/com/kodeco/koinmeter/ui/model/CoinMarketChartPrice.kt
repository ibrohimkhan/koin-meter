package com.kodeco.koinmeter.ui.model

import java.time.LocalDateTime

data class CoinMarketChartPrice(
    val date: LocalDateTime,
    val price: Double
)
