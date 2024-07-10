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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoin(favoriteCoin: FavoriteCoinEntity)

    @Delete
    suspend fun deleteFavoriteCoin(favoriteCoin: FavoriteCoinEntity)
}
