package com.kodeco.koinmeter.data.network.dto.coin

import com.kodeco.koinmeter.data.network.dto.coin.market.AllTimeHighDto
import com.kodeco.koinmeter.data.network.dto.coin.market.AllTimeLowDto
import com.kodeco.koinmeter.data.network.dto.coin.market.AthChangePercentageDto
import com.kodeco.koinmeter.data.network.dto.coin.market.AthDateDto
import com.kodeco.koinmeter.data.network.dto.coin.market.AtlChangePercentageDto
import com.kodeco.koinmeter.data.network.dto.coin.market.AtlDateDto
import com.kodeco.koinmeter.data.network.dto.coin.market.CurrentPriceDto
import com.kodeco.koinmeter.data.network.dto.coin.market.FullyDilutedValuationDto
import com.kodeco.koinmeter.data.network.dto.coin.market.High24HDto
import com.kodeco.koinmeter.data.network.dto.coin.market.Low24HDto
import com.kodeco.koinmeter.data.network.dto.coin.market.MarketCapDto
import com.kodeco.koinmeter.data.network.dto.coin.market.TotalVolumeDto
import com.squareup.moshi.Json

data class MarketDataDto(
    @Json(name = "current_price") val currentPrice: CurrentPriceDto? = null,
    val ath: AllTimeHighDto? = null,
    @Json(name = "ath_change_percentage") val athChangePercentage: AthChangePercentageDto? = null,
    @Json(name = "ath_date") val athDate: AthDateDto? = null,
    val atl: AllTimeLowDto? = null,
    @Json(name = "atl_change_percentage") val atlChangePercentage: AtlChangePercentageDto? = null,
    @Json(name = "atl_date") val atlDate: AtlDateDto? = null,
    @Json(name = "market_cap") val marketCap: MarketCapDto? = null,
    @Json(name = "market_cap_rank") val marketCapRank: Int? = null,
    @Json(name = "fully_diluted_valuation") val fullyDilutedValuation: FullyDilutedValuationDto? = null,
    @Json(name = "total_volume") val totalVolume: TotalVolumeDto? = null,
    @Json(name = "high_24h") val high24h: High24HDto? = null,
    @Json(name = "low_24h") val low24h: Low24HDto? = null,
    @Json(name = "price_change_24h") val priceChange24h: Double? = null,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage24h: Double? = null,
    @Json(name = "price_change_percentage_7d") val priceChangePercentage7d: Double? = null,
    @Json(name = "price_change_percentage_30d") val priceChangePercentage30d: Double? = null,
    @Json(name = "price_change_percentage_200d") val priceChangePercentage200d: Double? = null,
    @Json(name = "price_change_percentage_1y") val priceChangePercentage1y: Double? = null,
    @Json(name = "market_cap_change_24h") val marketCapChange24h: Double? = null,
    @Json(name = "market_cap_change_percentage_24h") val marketCapChangePercentage24h: Double? = null,
    @Json(name = "total_supply") val totalSupply: Double? = null,
    @Json(name = "max_supply") val maxSupply: Double? = null,
    @Json(name = "circulating_supply") val circulatingSupply: Double? = null,
    @Json(name = "last_updated") val lastUpdated: String? = null
)
