package com.kodeco.koinmeter.domain.repository

import com.kodeco.koinmeter.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface TopCoinsRepository {
    fun getTopCoins(timeframe: String): Flow<List<Coin>>
    fun getCoin(coinId: String): Flow<Coin?>

    fun getFavoriteCoins(): Flow<List<Coin>>
    suspend fun updateFavoriteStatus(coinId: String, isFavorite: Boolean)
}
