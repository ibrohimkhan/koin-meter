package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSource
import com.kodeco.koinmeter.data.mapper.DataMappers
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopCoinsRepositoryImpl(
    private val remoteDataSource: TopCoinsRemoteDataSource,
    private val localDataSource: TopCoinsLocalDataSource,
) : TopCoinsRepository {

    private var cachedTopCoins = emptyList<Coin>()

    override fun getTopCoins(timeframe: String): Flow<List<Coin>> = flow {
        val response = remoteDataSource.getTopCoins(timeframe)

        if (response.isSuccessful) {
            response.body()?.let { coinList ->
                val entity = DataMappers.topCoinsToEntity(coinList)
                localDataSource.insertCoins(entity)
                cachedTopCoins = coinList
            }
        }

        localDataSource.getAllCoins().collect { coins ->
            val topCoins = DataMappers.coinEntityToDomain(coins)

            if (topCoins.isEmpty()) emit(cachedTopCoins)
            else emit(topCoins)
        }
    }

    override fun getCoin(coinId: String): Flow<Coin?> = flow {
        val entity = localDataSource.getCoinById(coinId)

        if (entity != null) emit(DataMappers.coinEntityToDomain(entity))
        else emit(cachedTopCoins.find { it.id == coinId })
    }

    override fun getFavoriteCoins(): Flow<List<Coin>> = flow {
        localDataSource.getFavoriteCoins().collect { coins ->
            if (coins.isNullOrEmpty()) emit(cachedTopCoins.filter { it.isFavorite })
            else emit(DataMappers.coinEntityToDomain(coins))
        }
    }

    override suspend fun updateFavoriteStatus(coinId: String, isFavorite: Boolean) {
        localDataSource.updateFavoriteStatus(coinId, isFavorite)
        updateCachedTopCoins(coinId, isFavorite)
    }

    private fun updateCachedTopCoins(coinId: String, isFavorite: Boolean) {
        cachedTopCoins.find { it.id == coinId }?.copy(isFavorite = isFavorite)?.let { coin ->
            cachedTopCoins = cachedTopCoins.filter { it.id != coinId } + coin
        }
    }
}
