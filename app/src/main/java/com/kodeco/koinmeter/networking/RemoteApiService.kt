package com.kodeco.koinmeter.networking

import com.kodeco.koinmeter.model.Coin
import com.kodeco.koinmeter.model.CoinMarket
import com.kodeco.koinmeter.model.CoinMarketChartPrice
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
    ): Response<List<Coin>>

    @GET("coins/{coin_id}")
    suspend fun getCoinDetails(
        @Path("coin_id")
        coinId: String,

        @Query("localization")
        localization: Boolean = false,

        @Query("tickers")
        tickers: Boolean = false,

        @Query("market_data")
        marketData: Boolean = true,

        @Query("community_data")
        communityData: Boolean = false,

        @Query("developer_data")
        developerData: Boolean = false,

        @Query("sparkline")
        sparkline: Boolean = false,
    ): Response<CoinMarket>

    @GET("coins/{coin_id}/market_chart")
    suspend fun getCoinChartData(
        @Path("coin_id")
        coinId: String,

        @Query("vs_currency")
        vsCurrency: String = "usd",

        @Query("days")
        days: Int,

        @Query("precision")
        precision: Int = 2
    ): Response<List<CoinMarketChartPrice>>
}
