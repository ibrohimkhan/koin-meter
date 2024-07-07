package com.kodeco.koinmeter.networking.dto.coin

import com.kodeco.koinmeter.networking.dto.coin.market.AllTimeHigh
import com.kodeco.koinmeter.networking.dto.coin.market.AllTimeLow
import com.kodeco.koinmeter.networking.dto.coin.market.AthChangePercentage
import com.kodeco.koinmeter.networking.dto.coin.market.AthDate
import com.kodeco.koinmeter.networking.dto.coin.market.AtlChangePercentage
import com.kodeco.koinmeter.networking.dto.coin.market.AtlDate
import com.kodeco.koinmeter.networking.dto.coin.market.CurrentPrice
import com.kodeco.koinmeter.networking.dto.coin.market.FullyDilutedValuation
import com.kodeco.koinmeter.networking.dto.coin.market.High24H
import com.kodeco.koinmeter.networking.dto.coin.market.Low24H
import com.kodeco.koinmeter.networking.dto.coin.market.MarketCap
import com.kodeco.koinmeter.networking.dto.coin.market.TotalVolume
import com.squareup.moshi.Json

data class MarketData(
    @Json(name = "current_price") val currentPrice: CurrentPrice,
    val ath: AllTimeHigh,
    @Json(name = "ath_change_percentage") val athChangePercentage: AthChangePercentage,
    @Json(name = "ath_date") val athDate: AthDate,
    val atl: AllTimeLow,
    @Json(name = "atl_change_percentage") val atlChangePercentage: AtlChangePercentage,
    @Json(name = "atl_date") val atlDate: AtlDate,
    @Json(name = "market_cap") val marketCap: MarketCap,
    @Json(name = "market_cap_rank") val marketCapRank: Int,
    @Json(name = "fully_diluted_valuation") val fullyDilutedValuation: FullyDilutedValuation,
    @Json(name = "total_volume") val totalVolume: TotalVolume,
    @Json(name = "high_24h") val high24h: High24H,
    @Json(name = "low_24h") val low24h: Low24H,
    @Json(name = "price_change_24h") val priceChange24h: Double,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage24h: Double,
    @Json(name = "price_change_percentage_7d") val priceChangePercentage7d: Double,
    @Json(name = "price_change_percentage_30d") val priceChangePercentage30d: Double,
    @Json(name = "price_change_percentage_200d") val priceChangePercentage200d: Double,
    @Json(name = "price_change_percentage_1y") val priceChangePercentage1y: Double,
    @Json(name = "market_cap_change_24h") val marketCapChange24h: Long,
    @Json(name = "market_cap_change_percentage_24h") val marketCapChangePercentage24h: Double,
    @Json(name = "total_supply") val totalSupply: Double,
    @Json(name = "max_supply") val maxSupply: Double,
    @Json(name = "circulating_supply") val circulatingSupply: Double,
    @Json(name = "last_updated") val lastUpdated: String
)
