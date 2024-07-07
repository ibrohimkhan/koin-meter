package com.kodeco.koinmeter.networking.dto

import com.squareup.moshi.Json

data class CoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @Json(name = "current_price") val currentPrice: Double,
    @Json(name = "market_cap") val marketCap: Long,
    @Json(name = "market_cap_rank") val marketCapRank: Int,
    @Json(name = "fully_diluted_valuation") val fullyDilutedValuation: Long?,
    @Json(name = "total_volume") val totalVolume: Double,
    @Json(name = "high_24h") val high24h: Double,
    @Json(name = "low_24h") val low24h: Double,
    @Json(name = "price_change_24h") val priceChange24h: Double,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage24h: Double,
    @Json(name = "circulating_supply") val circulatingSupply: Double,
    @Json(name = "total_supply") val totalSupply: Double?,
    @Json(name = "max_supply") val maxSupply: Double?,
    val ath: Double,
    @Json(name = "ath_change_percentage") val athChangePercentage: Double,
    @Json(name = "ath_date") val athDate: String,
    val atl: Double,
    @Json(name = "atl_change_percentage") val atlChangePercentage: Double,
    @Json(name = "atl_date") val atlDate: String,
    @Json(name = "last_updated") val lastUpdated: String,
    @Json(name = "price_change_percentage_24h_in_currency") val priceChangePercentage24hInCurrency: Double? = null,
    @Json(name = "price_change_percentage_7d_in_currency") val priceChangePercentage7dInCurrency: Double? = null,
    @Json(name = "price_change_percentage_30d_in_currency") val priceChangePercentage30dInCurrency: Double? = null,
    @Json(name = "price_change_percentage_200d_in_currency") val priceChangePercentage200dInCurrency: Double? = null,
    @Json(name = "price_change_percentage_1y_in_currency") val priceChangePercentage1yInCurrency: Double? = null
)
