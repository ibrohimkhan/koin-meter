package com.kodeco.koinmeter.networking.dto

import com.kodeco.koinmeter.networking.dto.coin.DescriptionDto
import com.kodeco.koinmeter.networking.dto.coin.Image
import com.kodeco.koinmeter.networking.dto.coin.MarketDataDto
import com.squareup.moshi.Json

data class CoinMarketDto(
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val description: DescriptionDto? = null,
    val image: Image? = null,
    @Json(name = "market_cap_rank") val marketCapRank: Int? = null,
    @Json(name = "market_data") val marketData: MarketDataDto? = null,
    @Json(name = "last_updated") val lastUpdated: String? = null
)
