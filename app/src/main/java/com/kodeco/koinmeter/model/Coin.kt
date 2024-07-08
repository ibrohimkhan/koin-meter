package com.kodeco.koinmeter.model

data class Coin(
    val id: String,
    val name: String?,
    val symbol: String?,
    val image: String?,
    val currentPrice: Double?,
    val marketCap: Long?,
    val marketCapRank: Int?,
    val fullyDilutedValuation: Long?,
    val totalVolume: Double?,
    val high24h: Double?,
    val low24h: Double?,
    val priceChange24h: Double?,
    val priceChangePercentage24h: Double?,
    val circulatingSupply: Double?,
    val totalSupply: Double?,
    val maxSupply: Double?,
    val ath: Double?,
    val athChangePercentage: Double?,
    val athDate: String?,
    val atl: Double?,
    val atlChangePercentage: Double?,
    val atlDate: String?,
    val lastUpdated: String?,
    val priceChangePercentage24hInCurrency: Double?,
    val priceChangePercentage7dInCurrency: Double?,
    val priceChangePercentage30dInCurrency: Double?,
    val priceChangePercentage200dInCurrency: Double?,
    val priceChangePercentage1yInCurrency: Double?
)
