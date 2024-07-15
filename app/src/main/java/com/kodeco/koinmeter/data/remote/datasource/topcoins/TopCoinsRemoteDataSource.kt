package com.kodeco.koinmeter.data.remote.datasource.topcoins

import com.kodeco.koinmeter.domain.model.Coin
import retrofit2.Response

interface TopCoinsRemoteDataSource {
    suspend fun getTopCoins(timeframe: String): Response<List<Coin>>
}
