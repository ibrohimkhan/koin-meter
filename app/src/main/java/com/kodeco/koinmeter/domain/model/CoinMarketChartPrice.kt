package com.kodeco.koinmeter.domain.model

import java.time.LocalDateTime

data class CoinMarketChartPrice(
    val date: LocalDateTime,
    val price: Double
)
