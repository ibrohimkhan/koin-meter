package com.kodeco.koinmeter.data.repository

import android.util.Log
import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSource
import com.kodeco.koinmeter.data.mapper.toCoin
import com.kodeco.koinmeter.data.mapper.toCoinEntity
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class TopCoinsRepositoryImpl(
    private val remoteDataSource: TopCoinsRemoteDataSource,
    private val localDataSource: TopCoinsLocalDataSource,
) : TopCoinsRepository {

    private var cachedTopCoins = emptyList<Coin>()

    override suspend fun getTopCoins(timeframe: String): Flow<List<Coin>> {
        try {
            val response = remoteDataSource.getTopCoins(timeframe)

            if (response.isSuccessful) {
                response.body()?.let { coinList ->
                    val entities = coinList.map { it.toCoinEntity() }
                    localDataSource.insertCoins(entities)
                    cachedTopCoins = coinList
                }
            }
        } catch (e: Throwable) {
            Log.e("TopCoinsRepositoryImpl", "Error fetching top coins", e)
        }

        return localDataSource.getAllCoins()
            .map { entities ->
                if (entities.isNotEmpty()) entities.map { coinEntity -> coinEntity.toCoin() }
                else cachedTopCoins
            }
    }

    override suspend fun getCoin(coinId: String): Flow<Coin?> {
        return localDataSource.getCoinById(coinId)?.let {
            flowOf(it.toCoin())
        } ?: flowOf(cachedTopCoins.find { it.id == coinId })
    }
}
