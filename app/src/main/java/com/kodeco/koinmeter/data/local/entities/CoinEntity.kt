package com.kodeco.koinmeter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "symbol")
    val symbol: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "current_price")
    val currentPrice: Double?,

    @ColumnInfo(name = "market_cap")
    val marketCap: Long?,

    @ColumnInfo(name = "market_cap_rank")
    val marketCapRank: Int?,

    @ColumnInfo(name = "fully_diluted_valuation")
    val fullyDilutedValuation: Long?,

    @ColumnInfo(name = "total_volume")
    val totalVolume: Double?,

    @ColumnInfo(name = "high_24h")
    val high24h: Double?,

    @ColumnInfo(name = "low_24h")
    val low24h: Double?,

    @ColumnInfo(name = "price_change_24h")
    val priceChange24h: Double?,

    @ColumnInfo(name = "price_change_percentage_24h")
    val priceChangePercentage24h: Double?,

    @ColumnInfo(name = "circulating_supply")
    val circulatingSupply: Double?,

    @ColumnInfo(name = "total_supply")
    val totalSupply: Double?,

    @ColumnInfo(name = "max_supply")
    val maxSupply: Double?,

    @ColumnInfo(name = "all_time_high")
    val ath: Double?,

    @ColumnInfo(name = "ath_change_percentage")
    val athChangePercentage: Double?,

    @ColumnInfo(name = "ath_date")
    val athDate: String?,

    @ColumnInfo(name = "all_time_low")
    val atl: Double?,

    @ColumnInfo(name = "atl_change_percentage")
    val atlChangePercentage: Double?,

    @ColumnInfo(name = "atl_date")
    val atlDate: String?,

    @ColumnInfo(name = "last_updated")
    val lastUpdated: String?,

    @ColumnInfo(name = "price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: Double?,

    @ColumnInfo(name = "price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: Double?,

    @ColumnInfo(name = "price_change_percentage_30d_in_currency")
    val priceChangePercentage30dInCurrency: Double?,

    @ColumnInfo(name = "price_change_percentage_200d_in_currency")
    val priceChangePercentage200dInCurrency: Double?,

    @ColumnInfo(name = "price_change_percentage_1y_in_currency")
    val priceChangePercentage1yInCurrency: Double?,
)
