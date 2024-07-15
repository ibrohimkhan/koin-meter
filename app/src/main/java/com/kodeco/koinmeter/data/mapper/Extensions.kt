package com.kodeco.koinmeter.data.mapper

import com.kodeco.koinmeter.data.local.entities.CoinEntity
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import com.kodeco.koinmeter.data.local.entities.FavoriteCoinEntity
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice

fun List<CoinMarketChartPrice>.toCoinMarketChartEntity(
    coinId: String,
) = CoinMarketChartEntity(
    coinId = coinId,
    prices = this.map {
        CoinMarketChartEntity.Companion.CoinMarketChart(
            date = it.date,
            price = it.price
        )
    }
)

fun CoinMarketChartEntity.toCoinMarketChartPrice(): List<CoinMarketChartPrice> =
    this.prices.map {
        CoinMarketChartPrice(
            date = it.date,
            price = it.price
        )
    }

fun FavoriteCoinEntity.toCoin() = Coin(
    id = this.id,
    name = this.name,
    symbol = this.symbol,
    image = this.image,
    currentPrice = this.currentPrice,
    marketCap = this.marketCap,
    marketCapRank = this.marketCapRank,
    fullyDilutedValuation = this.fullyDilutedValuation,
    totalVolume = this.totalVolume,
    high24h = this.high24h,
    low24h = this.low24h,
    priceChange24h = this.priceChange24h,
    priceChangePercentage24h = this.priceChangePercentage24h,
    circulatingSupply = this.circulatingSupply,
    totalSupply = this.totalSupply,
    maxSupply = this.maxSupply,
    ath = this.ath,
    athChangePercentage = this.athChangePercentage,
    athDate = this.athDate,
    atl = this.atl,
    atlChangePercentage = this.atlChangePercentage,
    atlDate = this.atlDate,
    lastUpdated = this.lastUpdated,
    priceChangePercentage24hInCurrency = this.priceChangePercentage24hInCurrency,
    priceChangePercentage7dInCurrency = this.priceChangePercentage7dInCurrency,
    priceChangePercentage30dInCurrency = this.priceChangePercentage30dInCurrency,
    priceChangePercentage200dInCurrency = this.priceChangePercentage200dInCurrency,
    priceChangePercentage1yInCurrency = this.priceChangePercentage1yInCurrency,
)

fun Coin.toFavoriteCoinEntity() = FavoriteCoinEntity(
    id = this.id,
    name = this.name,
    symbol = this.symbol,
    image = this.image,
    currentPrice = this.currentPrice,
    marketCap = this.marketCap,
    marketCapRank = this.marketCapRank,
    fullyDilutedValuation = this.fullyDilutedValuation,
    totalVolume = this.totalVolume,
    high24h = this.high24h,
    low24h = this.low24h,
    priceChange24h = this.priceChange24h,
    priceChangePercentage24h = this.priceChangePercentage24h,
    circulatingSupply = this.circulatingSupply,
    totalSupply = this.totalSupply,
    maxSupply = this.maxSupply,
    ath = this.ath,
    athChangePercentage = this.athChangePercentage,
    athDate = this.athDate,
    atl = this.atl,
    atlChangePercentage = this.atlChangePercentage,
    atlDate = this.atlDate,
    lastUpdated = this.lastUpdated,
    priceChangePercentage24hInCurrency = this.priceChangePercentage24hInCurrency,
    priceChangePercentage7dInCurrency = this.priceChangePercentage7dInCurrency,
    priceChangePercentage30dInCurrency = this.priceChangePercentage30dInCurrency,
    priceChangePercentage200dInCurrency = this.priceChangePercentage200dInCurrency,
    priceChangePercentage1yInCurrency = this.priceChangePercentage1yInCurrency,
)

fun CoinEntity.toCoin() = Coin(
    id = this.id,
    name = this.name,
    symbol = this.symbol,
    image = this.image,
    currentPrice = this.currentPrice,
    marketCap = this.marketCap,
    marketCapRank = this.marketCapRank,
    fullyDilutedValuation = this.fullyDilutedValuation,
    totalVolume = this.totalVolume,
    high24h = this.high24h,
    low24h = this.low24h,
    priceChange24h = this.priceChange24h,
    priceChangePercentage24h = this.priceChangePercentage24h,
    circulatingSupply = this.circulatingSupply,
    totalSupply = this.totalSupply,
    maxSupply = this.maxSupply,
    ath = this.ath,
    athChangePercentage = this.athChangePercentage,
    athDate = this.athDate,
    atl = this.atl,
    atlChangePercentage = this.atlChangePercentage,
    atlDate = this.atlDate,
    lastUpdated = this.lastUpdated,
    priceChangePercentage24hInCurrency = this.priceChangePercentage24hInCurrency,
    priceChangePercentage7dInCurrency = this.priceChangePercentage7dInCurrency,
    priceChangePercentage30dInCurrency = this.priceChangePercentage30dInCurrency,
    priceChangePercentage200dInCurrency = this.priceChangePercentage200dInCurrency,
    priceChangePercentage1yInCurrency = this.priceChangePercentage1yInCurrency,
)

fun Coin.toCoinEntity() = CoinEntity(
    id = this.id,
    name = this.name,
    symbol = this.symbol,
    image = this.image,
    currentPrice = this.currentPrice,
    marketCap = this.marketCap,
    marketCapRank = this.marketCapRank,
    fullyDilutedValuation = this.fullyDilutedValuation,
    totalVolume = this.totalVolume,
    high24h = this.high24h,
    low24h = this.low24h,
    priceChange24h = this.priceChange24h,
    priceChangePercentage24h = this.priceChangePercentage24h,
    circulatingSupply = this.circulatingSupply,
    totalSupply = this.totalSupply,
    maxSupply = this.maxSupply,
    ath = this.ath,
    athChangePercentage = this.athChangePercentage,
    athDate = this.athDate,
    atl = this.atl,
    atlChangePercentage = this.atlChangePercentage,
    atlDate = this.atlDate,
    lastUpdated = this.lastUpdated,
    priceChangePercentage24hInCurrency = this.priceChangePercentage24hInCurrency,
    priceChangePercentage7dInCurrency = this.priceChangePercentage7dInCurrency,
    priceChangePercentage30dInCurrency = this.priceChangePercentage30dInCurrency,
    priceChangePercentage200dInCurrency = this.priceChangePercentage200dInCurrency,
    priceChangePercentage1yInCurrency = this.priceChangePercentage1yInCurrency,
)
