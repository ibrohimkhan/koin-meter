package com.kodeco.koinmeter.model

import java.time.LocalDateTime

data class CoinMarketChartPrice(
    val date: LocalDateTime,
    val price: Double
)
