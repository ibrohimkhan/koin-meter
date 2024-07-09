package com.kodeco.koinmeter.data.remote.dto

import com.squareup.moshi.Json

data class CoinDto(
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val image: String? = null,

    @Json(name = "current_price")
    val currentPrice: Double? = null,

    @Json(name = "market_cap")
    val marketCap: Long? = null,

    @Json(name = "market_cap_rank")
    val marketCapRank: Int? = null,

    @Json(name = "fully_diluted_valuation")
    val fullyDilutedValuation: Long? = null,

    @Json(name = "total_volume")
    val totalVolume: Double? = null,

    @Json(name = "high_24h")
    val high24h: Double? = null,

    @Json(name = "low_24h")
    val low24h: Double? = null,

    @Json(name = "price_change_24h")
    val priceChange24h: Double? = null,

    @Json(name = "price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null,

    @Json(name = "circulating_supply")
    val circulatingSupply: Double? = null,

    @Json(name = "total_supply")
    val totalSupply: Double? = null,

    @Json(name = "max_supply")
    val maxSupply: Double? = null,

    val ath: Double? = null,

    @Json(name = "ath_change_percentage")
    val athChangePercentage: Double? = null,

    @Json(name = "ath_date")
    val athDate: String? = null,

    val atl: Double? = null,

    @Json(name = "atl_change_percentage")
    val atlChangePercentage: Double? = null,

    @Json(name = "atl_date")
    val atlDate: String? = null,

    @Json(name = "last_updated")
    val lastUpdated: String? = null,

    @Json(name = "price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: Double? = null,

    @Json(name = "price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: Double? = null,

    @Json(name = "price_change_percentage_30d_in_currency")
    val priceChangePercentage30dInCurrency: Double? = null,

    @Json(name = "price_change_percentage_200d_in_currency")
    val priceChangePercentage200dInCurrency: Double? = null,

    @Json(name = "price_change_percentage_1y_in_currency")
    val priceChangePercentage1yInCurrency: Double? = null
)
