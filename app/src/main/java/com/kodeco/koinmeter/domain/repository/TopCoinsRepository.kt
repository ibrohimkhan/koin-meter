package com.kodeco.koinmeter.domain.repository

import com.kodeco.koinmeter.domain.model.Coin

interface TopCoinsRepository {
    suspend fun getTopCoins(timeframe: String): List<Coin>
    suspend fun getCoin(coinId: String): Coin?
}
