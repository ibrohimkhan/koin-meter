package com.kodeco.koinmeter.data.remote.adapters

import com.kodeco.koinmeter.ui.model.Coin
import com.kodeco.koinmeter.data.remote.dto.CoinDto
import com.squareup.moshi.FromJson

class CoinAdapter {
    @FromJson
    fun fromJson(coinDtoList: List<CoinDto>): List<Coin> = coinDtoList.map { coin ->
        Coin(
            id = coin.id,
            name = coin.name,
            symbol = coin.symbol,
            image = coin.image,
            currentPrice = coin.currentPrice,
            marketCap = coin.marketCap,
            marketCapRank = coin.marketCapRank,
            fullyDilutedValuation = coin.fullyDilutedValuation,
            totalVolume = coin.totalVolume,
            high24h = coin.high24h,
            low24h = coin.low24h,
            priceChange24h = coin.priceChange24h,
            priceChangePercentage24h = coin.priceChangePercentage24h,
            circulatingSupply = coin.circulatingSupply,
            totalSupply = coin.totalSupply,
            maxSupply = coin.maxSupply,
            ath = coin.ath,
            athChangePercentage = coin.athChangePercentage,
            athDate = coin.athDate,
            atl = coin.atl,
            atlChangePercentage = coin.atlChangePercentage,
            atlDate = coin.atlDate,
            lastUpdated = coin.lastUpdated,
            priceChangePercentage24hInCurrency = coin.priceChangePercentage24hInCurrency,
            priceChangePercentage7dInCurrency = coin.priceChangePercentage7dInCurrency,
            priceChangePercentage30dInCurrency = coin.priceChangePercentage30dInCurrency,
            priceChangePercentage200dInCurrency = coin.priceChangePercentage200dInCurrency,
            priceChangePercentage1yInCurrency = coin.priceChangePercentage1yInCurrency
        )
    }
}
