package com.kodeco.koinmeter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodeco.koinmeter.data.local.entities.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins")
    fun getAllCoins(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM coins WHERE id = :coinId")
    suspend fun getCoinById(coinId: String): CoinEntity?

    @Query("UPDATE coins SET is_favorite = :isFavorite WHERE id = :coinId")
    suspend fun updateFavoriteStatus(coinId: String, isFavorite: Boolean)

    @Query("DELETE FROM coins")
    suspend fun deleteAllCoins()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: CoinEntity)
}
