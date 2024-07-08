package com.kodeco.koinmeter.data.model

import java.time.LocalDateTime

data class CoinMarketChartPrice(
    val date: LocalDateTime,
    val price: Double
)
