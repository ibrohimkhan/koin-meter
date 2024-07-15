package com.kodeco.koinmeter.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodeco.koinmeter.data.local.entities.FavoriteCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCoinDao {

    @Query("SELECT * FROM favorite_coins")
    fun getAllFavoriteCoins(): Flow<List<FavoriteCoinEntity>>

    @Query("SELECT id FROM favorite_coins")
    suspend fun getAllFavoriteCoinIds(): List<String>

    @Query("SELECT COUNT(*) FROM favorite_coins WHERE id = :coinId")
    fun containsFavoriteCoin(coinId: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoin(favoriteCoin: FavoriteCoinEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoins(favoriteCoins: List<FavoriteCoinEntity>)

    @Delete
    suspend fun deleteFavoriteCoin(favoriteCoin: FavoriteCoinEntity)
}
