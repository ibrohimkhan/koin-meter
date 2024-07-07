package com.kodeco.koinmeter.model

import java.time.LocalDateTime

data class MarketChartPrice(
    val date: LocalDateTime,
    val price: Double
)
