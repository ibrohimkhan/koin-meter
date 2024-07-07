package com.kodeco.koinmeter.networking

import com.kodeco.koinmeter.networking.dto.CoinChartDto
import com.kodeco.koinmeter.networking.dto.CoinDto
import com.kodeco.koinmeter.networking.dto.CoinMarketDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApiService {
    @GET("coins/markets")
    suspend fun getTopCoins(
        @Query("vs_currency")
        vsCurrency: String = "usd",

        @Query("order")
        order: String = "market_cap_desc",

        @Query("per_page")
        perPage: Int = 250,

        @Query("page")
        page: Int = 1,

        @Query("price_change_percentage")
        percentageTimeframe: String
    ): Response<List<CoinDto>>

    @GET("coins/{coin_id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinDetails(@Path("coin_id") coinId: String): Response<CoinMarketDto>

    @GET("coins/{coin_id}/market_chart?vs_currency=usd&days={days}&precision=2")
    suspend fun getCoinChartData(@Path("coin_id") coinId: String, @Path("days") days: Int): Response<List<CoinChartDto>>
}
