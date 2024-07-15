package com.kodeco.koinmeter.mocks

import com.kodeco.koinmeter.data.local.entities.CoinEntity
import com.kodeco.koinmeter.data.local.entities.FavoriteCoinEntity
import com.kodeco.koinmeter.data.remote.dto.CoinChartDto
import com.kodeco.koinmeter.data.remote.dto.CoinDto
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import java.time.ZoneOffset

val expectedCoinBtc = mockk<Coin> {
    every { id } returns "bitcoin"
    every { name } returns "Bitcoin"
    every { symbol } returns "BTC"
}

val expectedCoinEth = mockk<Coin> {
    every { id } returns "ethereum"
    every { name } returns "Ethereum"
    every { symbol } returns "ETH"
}

val expectedCoinSol = mockk<Coin> {
    every { id } returns "solana"
    every { name } returns "Solana"
    every { symbol } returns "SOL"
}

val expectedCoinDot = mockk<Coin> {
    every { id } returns "polkadot"
    every { name } returns "Polkadot"
    every { symbol } returns "DOT"
}

val expectedCoinAtom = mockk<Coin> {
    every { id } returns "cosmos"
    every { name } returns "Cosmos"
    every { symbol } returns "ATOM"
}

val expectedCoinManta = mockk<Coin> {
    every { id } returns "manta"
    every { name } returns "Manta"
    every { symbol } returns "MANTA"
}

val expectedCoinNeuro = mockk<Coin> {
    every { id } returns "neuro"
    every { name } returns "Neuro"
    every { symbol } returns "NEURO"
}

val expectedCoinKsm = mockk<Coin> {
    every { id } returns "kusama"
    every { name } returns "Kusama"
    every { symbol } returns "KSM"
}

val expectedCoinLtc = mockk<Coin> {
    every { id } returns "litecoin"
    every { name } returns "LiteCoin"
    every { symbol } returns "LTC"
}

val expectedCoinKint = mockk<Coin> {
    every { id } returns "kintsugi"
    every { name } returns "Kintsugi"
    every { symbol } returns "KINT"
}

val expectedCoinKrest = mockk<Coin> {
    every { id } returns "krest"
    every { name } returns "Krest"
    every { symbol } returns "KREST"
}

val expectedCoinList = listOf(
    expectedCoinBtc,
    expectedCoinEth,
    expectedCoinSol,
    expectedCoinDot,
    expectedCoinAtom,
    expectedCoinManta,
    expectedCoinNeuro,
    expectedCoinKsm,
    expectedCoinLtc,
    expectedCoinKint,
    expectedCoinKrest
)

val expectedCoinBtcEntity = mockk<CoinEntity> {
    every { id } returns "bitcoin"
    every { name } returns "Bitcoin"
    every { symbol } returns "BTC"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinEthEntity = mockk<CoinEntity> {
    every { id } returns "ethereum"
    every { name } returns "Ethereum"
    every { symbol } returns "ETH"
}

val expectedCoinSolEntity = mockk<CoinEntity> {
    every { id } returns "solana"
    every { name } returns "Solana"
    every { symbol } returns "SOL"
}

val expectedCoinDotEntity = mockk<CoinEntity> {
    every { id } returns "polkadot"
    every { name } returns "Polkadot"
    every { symbol } returns "DOT"
}

val expectedCoinAtomEntity = mockk<CoinEntity> {
    every { id } returns "cosmos"
    every { name } returns "Cosmos"
    every { symbol } returns "ATOM"
}

val expectedCoinListEntity = listOf(
    expectedCoinBtcEntity,
    expectedCoinEthEntity,
    expectedCoinSolEntity,
    expectedCoinDotEntity,
    expectedCoinAtomEntity
)

val expectedFavoriteCoinBtcEntity = mockk<FavoriteCoinEntity> {
    every { id } returns "bitcoin"
    every { name } returns "Bitcoin"
    every { symbol } returns "BTC"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedFavoriteCoinEthEntity = mockk<FavoriteCoinEntity> {
    every { id } returns "ethereum"
    every { name } returns "Ethereum"
    every { symbol } returns "ETH"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedFavoriteCoinSolEntity = mockk<FavoriteCoinEntity> {
    every { id } returns "solana"
    every { name } returns "Solana"
    every { symbol } returns "SOL"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedFavoriteCoinListEntity = listOf(
    expectedFavoriteCoinBtcEntity,
    expectedFavoriteCoinEthEntity,
    expectedFavoriteCoinSolEntity,
    expectedFavoriteCoinSolEntity,
    expectedFavoriteCoinSolEntity,
)

val expectedCoinMarketChartPriceList = listOf(
    CoinMarketChartPrice(
        LocalDateTime.now(),
        10000.0
    ),
    CoinMarketChartPrice(
        LocalDateTime.now(),
        20000.0
    ),
    CoinMarketChartPrice(
        LocalDateTime.now(),
        15000.0
    ),
)

val expectedCoinDtoBtc = mockk<CoinDto> {
    every { id } returns "bitcoin"
    every { name } returns "Bitcoin"
    every { symbol } returns "BTC"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinDtoEth = mockk<CoinDto> {
    every { id } returns "ethereum"
    every { name } returns "Ethereum"
    every { symbol } returns "ETH"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinDtoSol = mockk<CoinDto> {
    every { id } returns "solana"
    every { name } returns "Solana"
    every { symbol } returns "SOL"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinBtcAdapter = mockk<Coin> {
    every { id } returns "bitcoin"
    every { name } returns "Bitcoin"
    every { symbol } returns "BTC"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinEthAdapter = mockk<Coin> {
    every { id } returns "ethereum"
    every { name } returns "Ethereum"
    every { symbol } returns "ETH"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinSolAdapter = mockk<Coin> {
    every { id } returns "solana"
    every { name } returns "Solana"
    every { symbol } returns "SOL"
    every { currentPrice } returns 10000.0
    every { marketCap } returns 1000000000
    every { marketCapRank } returns 1
    every { fullyDilutedValuation } returns 1000000000
    every { totalVolume } returns 100000000.0
    every { high24h } returns 10000.0
    every { low24h } returns 1000.0
    every { priceChange24h } returns 100.0
    every { priceChangePercentage24h } returns 10.0
    every { circulatingSupply } returns 1000000.0
    every { totalSupply } returns 1000000.0
    every { maxSupply } returns 1000000.0
    every { ath } returns 10000.0
    every { athChangePercentage } returns 10.0
    every { athDate } returns "2023-01-01"
    every { atl } returns 1000.0
    every { atlChangePercentage } returns 10.0
    every { atlDate } returns "2023-01-01"
    every { lastUpdated } returns "2023-01-01"
    every { priceChangePercentage24hInCurrency } returns 10.0
    every { priceChangePercentage7dInCurrency } returns 10.0
    every { priceChangePercentage30dInCurrency } returns 10.0
    every { priceChangePercentage200dInCurrency } returns 10.0
    every { priceChangePercentage1yInCurrency } returns 10.0
    every { image } returns "https://example.com/bitcoin.png"
}

val expectedCoinDtoList = listOf(
    expectedCoinDtoBtc,
    expectedCoinDtoEth,
    expectedCoinDtoSol
)

val expectedCoinDtoListDomain = listOf(
    expectedCoinBtcAdapter,
    expectedCoinEthAdapter,
    expectedCoinSolAdapter
)

val coinMarketChartDto = mockk<CoinChartDto> {
    val date = LocalDateTime.of(2024, 1, 1, 0, 0, 0)
        .toEpochSecond(ZoneOffset.UTC) * 1000.0

    every { prices } returns listOf(
        listOf(date, 1000.0),
    )
}

val expectedCoinMarketChartPriceBtc = CoinMarketChartPrice(
    LocalDateTime.of(2024, 1, 1, 0, 0, 0),
    1000.0
)

val coinMarketChartDtoExpected = CoinChartDto(
    prices = listOf(
        listOf(
            LocalDateTime.of(2024, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC) * 1000.0,
            1000.0
        ),
    )
)
