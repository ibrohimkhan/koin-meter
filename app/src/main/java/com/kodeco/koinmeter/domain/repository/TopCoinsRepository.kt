package com.kodeco.koinmeter.domain.repository

import com.kodeco.koinmeter.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface TopCoinsRepository {
    suspend fun getTopCoins(timeframe: String): Flow<List<Coin>>
    suspend fun getCoin(coinId: String): Flow<Coin?>
}
