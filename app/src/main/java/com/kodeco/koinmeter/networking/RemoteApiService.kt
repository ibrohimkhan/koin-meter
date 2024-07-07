package com.kodeco.koinmeter.networking

import com.kodeco.koinmeter.networking.dto.CoinChartDto
import com.kodeco.koinmeter.networking.dto.CoinDto
import com.kodeco.koinmeter.networking.dto.CoinMarketDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApiService {
    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=250&page=1&price_change_percentage={percentage_timeframe}")
    suspend fun getTopCoins(@Path("percentage_timeframe") percentageTimeframe: String): Response<List<CoinDto>>

    @GET("coins/{coin_id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinDetails(@Path("coin_id") coinId: String): Response<CoinMarketDto>

    @GET("coins/{coin_id}/market_chart?vs_currency=usd&days={days}&precision=2")
    suspend fun getCoinChartData(@Path("coin_id") coinId: String, @Path("days") days: Int): Response<List<CoinChartDto>>
}
