package com.kodeco.koinmeter.data.remote.adapters

import com.kodeco.koinmeter.data.remote.dto.CoinChartDto
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class CoinMarketChartPriceAdapter {
    @FromJson
    fun fromJson(coinChartDto: CoinChartDto): List<CoinMarketChartPrice> {
        if (coinChartDto.prices.isNullOrEmpty()) return emptyList()

        return coinChartDto.prices.map {
            CoinMarketChartPrice(
                date = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(it[0].toLong()),
                    ZoneOffset.UTC
                ),
                price = it[1]
            )
        }
    }

    @ToJson
    fun toJson(coinMarketChartPrice: List<CoinMarketChartPrice>): CoinChartDto {
        return CoinChartDto(
            prices = coinMarketChartPrice.map {
                listOf((it.date.toEpochSecond(ZoneOffset.UTC) * 1000).toDouble(), it.price)
            }
        )
    }
}
