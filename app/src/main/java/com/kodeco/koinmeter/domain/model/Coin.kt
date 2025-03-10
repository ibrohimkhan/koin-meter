package com.kodeco.koinmeter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    val id: String,
    val name: String? = null,
    val symbol: String? = null,
    val image: String? = null,
    val currentPrice: Double? = null,
    val marketCap: Long? = null,
    val marketCapRank: Int? = null,
    val fullyDilutedValuation: Double? = null,
    val totalVolume: Double? = null,
    val high24h: Double? = null,
    val low24h: Double? = null,
    val priceChange24h: Double? = null,
    val priceChangePercentage24h: Double? = null,
    val circulatingSupply: Double? = null,
    val totalSupply: Double? = null,
    val maxSupply: Double? = null,
    val ath: Double? = null,
    val athChangePercentage: Double? = null,
    val athDate: String? = null,
    val atl: Double? = null,
    val atlChangePercentage: Double? = null,
    val atlDate: String? = null,
    val lastUpdated: String? = null,
    val priceChangePercentage24hInCurrency: Double? = null,
    val priceChangePercentage7dInCurrency: Double? = null,
    val priceChangePercentage30dInCurrency: Double? = null,
    val priceChangePercentage200dInCurrency: Double? = null,
    val priceChangePercentage1yInCurrency: Double? = null,
) : Parcelable
