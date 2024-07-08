package com.kodeco.koinmeter.data.remote.adapters

import com.kodeco.koinmeter.model.CoinMarket
import com.kodeco.koinmeter.model.Image
import com.kodeco.koinmeter.data.remote.dto.CoinMarketDto
import com.squareup.moshi.FromJson

class CoinMarketAdapter {
    @FromJson
    fun fromJson(coinMarketDto: CoinMarketDto): CoinMarket = CoinMarket(
            id = coinMarketDto.id,
            name = coinMarketDto.name,
            symbol = coinMarketDto.symbol,
            description = coinMarketDto.description?.en,
            image = coinMarketDto.image?.let {
                Image(
                    thumb = it.thumb,
                    small = it.small,
                    large = it.large
                )
            },
            marketCapRank = coinMarketDto.marketCapRank,
            currentPrice = coinMarketDto.marketData?.currentPrice?.usd,
            ath = coinMarketDto.marketData?.ath?.usd,
            athChangePercentage = coinMarketDto.marketData?.athChangePercentage?.usd,
            athDate = coinMarketDto.marketData?.athDate?.usd,
            atl = coinMarketDto.marketData?.atl?.usd,
            atlChangePercentage = coinMarketDto.marketData?.atlChangePercentage?.usd,
            atlDate = coinMarketDto.marketData?.atlDate?.usd,
            marketCap = coinMarketDto.marketData?.marketCap?.usd,
            marketDataCapRank = coinMarketDto.marketData?.marketCapRank,
            fullyDilutedValuation = coinMarketDto.marketData?.fullyDilutedValuation?.usd,
            totalVolume = coinMarketDto.marketData?.totalVolume?.usd,
            high24h = coinMarketDto.marketData?.high24h?.usd,
            low24h = coinMarketDto.marketData?.low24h?.usd,
            priceChange24h = coinMarketDto.marketData?.priceChange24h,
            priceChangePercentage24h = coinMarketDto.marketData?.priceChangePercentage24h,
            priceChangePercentage7d = coinMarketDto.marketData?.priceChangePercentage7d,
            priceChangePercentage30d = coinMarketDto.marketData?.priceChangePercentage30d,
            priceChangePercentage200d = coinMarketDto.marketData?.priceChangePercentage200d,
            priceChangePercentage1y = coinMarketDto.marketData?.priceChangePercentage1y,
            marketCapChange24h = coinMarketDto.marketData?.marketCapChange24h,
            marketCapChangePercentage24h = coinMarketDto.marketData?.marketCapChangePercentage24h,
            totalSupply = coinMarketDto.marketData?.totalSupply,
            maxSupply = coinMarketDto.marketData?.maxSupply,
            circulatingSupply = coinMarketDto.marketData?.circulatingSupply,
            marketLastUpdated = coinMarketDto.marketData?.lastUpdated,
            lastUpdated = coinMarketDto.lastUpdated
        )
}
