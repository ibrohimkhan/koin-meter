package com.kodeco.koinmeter.data.local.datasource.favoritecoins

import com.kodeco.koinmeter.data.local.entities.FavoriteCoinEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteCoinsLocalDataSource {
    fun getAllFavoriteCoins(): Flow<List<FavoriteCoinEntity>>
    suspend fun getAllFavoriteCoinIds(): List<String>
    fun containsFavoriteCoin(coinId: String): Flow<Int>
    suspend fun insertFavoriteCoin(favoriteCoin: FavoriteCoinEntity)
    suspend fun insertFavoriteCoins(favoriteCoins: List<FavoriteCoinEntity>)
    suspend fun deleteFavoriteCoin(favoriteCoin: FavoriteCoinEntity)
}
