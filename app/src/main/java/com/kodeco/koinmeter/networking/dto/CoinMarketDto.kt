package com.kodeco.koinmeter.networking.dto

import com.kodeco.koinmeter.networking.dto.coin.Description
import com.kodeco.koinmeter.networking.dto.coin.Image
import com.kodeco.koinmeter.networking.dto.coin.MarketData
import com.squareup.moshi.Json

data class CoinMarketDto(
    val id: String,
    val symbol: String,
    val name: String,
    val description: Description,
    val image: Image,
    @Json(name = "market_cap_rank") val marketCapRank: Int,
    @Json(name = "market_data") val marketData: MarketData,
    @Json(name = "last_updated") val lastUpdated: String
)
