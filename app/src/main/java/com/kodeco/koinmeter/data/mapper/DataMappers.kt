package com.kodeco.koinmeter.data.mapper

import com.kodeco.koinmeter.data.local.entities.CoinEntity
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice

object DataMappers {
    fun coinMarketChartPriceToEntity(
        coinId: String,
        prices: List<CoinMarketChartPrice>
    ) = CoinMarketChartEntity(
        coinId = coinId,
        prices = prices.map {
            CoinMarketChartEntity.Companion.CoinMarketChart(
                date = it.date,
                price = it.price
            )
        }
    )

    fun coinMarketChartEntityToDomain(entity: CoinMarketChartEntity): List<CoinMarketChartPrice> =
        entity.prices.map {
            CoinMarketChartPrice(
                date = it.date,
                price = it.price
            )
        }

    fun topCoinsToEntity(topCoins: List<Coin>): List<CoinEntity> = topCoins.map {
        CoinEntity(
            id = it.id,
            name = it.name,
            symbol = it.symbol,
            image = it.image,
            currentPrice = it.currentPrice,
            marketCap = it.marketCap,
            marketCapRank = it.marketCapRank,
            fullyDilutedValuation = it.fullyDilutedValuation,
            totalVolume = it.totalVolume,
            high24h = it.high24h,
            low24h = it.low24h,
            priceChange24h = it.priceChange24h,
            priceChangePercentage24h = it.priceChangePercentage24h,
            circulatingSupply = it.circulatingSupply,
            totalSupply = it.totalSupply,
            maxSupply = it.maxSupply,
            ath = it.ath,
            athChangePercentage = it.athChangePercentage,
            athDate = it.athDate,
            atl = it.atl,
            atlChangePercentage = it.atlChangePercentage,
            atlDate = it.atlDate,
            lastUpdated = it.lastUpdated,
            priceChangePercentage24hInCurrency = it.priceChangePercentage24hInCurrency,
            priceChangePercentage7dInCurrency = it.priceChangePercentage7dInCurrency,
            priceChangePercentage30dInCurrency = it.priceChangePercentage30dInCurrency,
            priceChangePercentage200dInCurrency = it.priceChangePercentage200dInCurrency,
            priceChangePercentage1yInCurrency = it.priceChangePercentage1yInCurrency,
            isFavorite = it.isFavorite
        )
    }

    fun coinEntityToDomain(entities: List<CoinEntity>): List<Coin> = entities.map { entity ->
        coinEntityToDomain(entity)
    }

    fun coinEntityToDomain(entity: CoinEntity): Coin = Coin(
        id = entity.id,
        name = entity.name,
        symbol = entity.symbol,
        image = entity.image,
        currentPrice = entity.currentPrice,
        marketCap = entity.marketCap,
        marketCapRank = entity.marketCapRank,
        fullyDilutedValuation = entity.fullyDilutedValuation,
        totalVolume = entity.totalVolume,
        high24h = entity.high24h,
        low24h = entity.low24h,
        priceChange24h = entity.priceChange24h,
        priceChangePercentage24h = entity.priceChangePercentage24h,
        circulatingSupply = entity.circulatingSupply,
        totalSupply = entity.totalSupply,
        maxSupply = entity.maxSupply,
        ath = entity.ath,
        athChangePercentage = entity.athChangePercentage,
        athDate = entity.athDate,
        atl = entity.atl,
        atlChangePercentage = entity.atlChangePercentage,
        atlDate = entity.atlDate,
        lastUpdated = entity.lastUpdated,
        priceChangePercentage24hInCurrency = entity.priceChangePercentage24hInCurrency,
        priceChangePercentage7dInCurrency = entity.priceChangePercentage7dInCurrency,
        priceChangePercentage30dInCurrency = entity.priceChangePercentage30dInCurrency,
        priceChangePercentage200dInCurrency = entity.priceChangePercentage200dInCurrency,
        priceChangePercentage1yInCurrency = entity.priceChangePercentage1yInCurrency,
        isFavorite = entity.isFavorite
    )
}
