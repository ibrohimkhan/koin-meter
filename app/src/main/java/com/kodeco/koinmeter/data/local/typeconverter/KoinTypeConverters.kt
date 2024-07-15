package com.kodeco.koinmeter.data.local.typeconverter

import androidx.room.TypeConverter
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity.Companion.CoinMarketChart
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.java.KoinJavaComponent.inject

class KoinTypeConverters {
    private val moshi: Moshi by inject(Moshi::class.java)

    private val type = Types.newParameterizedType(List::class.java, CoinMarketChart::class.java)
    private val adapter = moshi.adapter<List<CoinMarketChart>>(type)

    @TypeConverter
    fun fromString(value: String): List<CoinMarketChart> = adapter.fromJson(value).orEmpty()

    @TypeConverter
    fun toString(value: List<CoinMarketChart>): String = adapter.toJson(value)
}
