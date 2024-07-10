package com.kodeco.koinmeter.domain.repository

import com.kodeco.koinmeter.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface FavoriteCoinsRepository {
    fun getAllFavoriteCoins(): Flow<List<Coin>>
    suspend fun insertFavoriteCoin(coin: Coin)
    suspend fun deleteFavoriteCoin(coin: Coin)
}
