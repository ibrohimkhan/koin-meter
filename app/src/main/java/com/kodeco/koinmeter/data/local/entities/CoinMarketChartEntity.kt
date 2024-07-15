package com.kodeco.koinmeter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kodeco.koinmeter.data.local.typeconverter.KoinTypeConverters
import java.time.LocalDateTime

@TypeConverters(KoinTypeConverters::class)
@Entity(tableName = "coin_market_charts")
data class CoinMarketChartEntity(
    @PrimaryKey
    @ColumnInfo(name = "coin_id")
    val coinId: String,

    @ColumnInfo(name = "prices")
    val prices: List<CoinMarketChart>
) {
    companion object {
        data class CoinMarketChart(
            val date: LocalDateTime,
            val price: Double
        )
    }
}
