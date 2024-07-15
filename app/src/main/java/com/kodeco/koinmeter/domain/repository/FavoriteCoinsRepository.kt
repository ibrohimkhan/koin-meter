package com.kodeco.koinmeter.domain.repository

import com.kodeco.koinmeter.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface FavoriteCoinsRepository {
    fun getAllFavoriteCoins(): Flow<List<Coin>>
    fun containsFavoriteCoin(coinId: String): Flow<Int>
    suspend fun insertFavoriteCoin(coin: Coin)
    suspend fun deleteFavoriteCoin(coin: Coin)
}
