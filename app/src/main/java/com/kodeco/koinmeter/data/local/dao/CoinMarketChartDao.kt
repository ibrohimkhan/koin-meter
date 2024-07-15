package com.kodeco.koinmeter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinMarketChartDao {

    @Query("SELECT * FROM coin_market_charts")
    fun getAllCoinMarketCharts(): Flow<List<CoinMarketChartEntity>>

    @Query("SELECT * FROM coin_market_charts WHERE coin_id = :coinId")
    suspend fun getCoinMarketChartByCoinId(coinId: String): CoinMarketChartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinMarketCharts(coinMarketCharts: List<CoinMarketChartEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinMarketChart(coinMarketChart: CoinMarketChartEntity)

    @Query("DELETE FROM coin_market_charts")
    suspend fun deleteAllCoinMarketCharts()
}
