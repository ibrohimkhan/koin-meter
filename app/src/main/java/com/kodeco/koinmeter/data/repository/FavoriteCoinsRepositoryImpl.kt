package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.favoritecoins.FavoriteCoinsLocalDataSource
import com.kodeco.koinmeter.data.mapper.toCoin
import com.kodeco.koinmeter.data.mapper.toFavoriteCoinEntity
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.FavoriteCoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteCoinsRepositoryImpl(
    private val localDataSource: FavoriteCoinsLocalDataSource
) : FavoriteCoinsRepository {

    override fun getAllFavoriteCoins(): Flow<List<Coin>> {
        return localDataSource.getAllFavoriteCoins()
            .map {
                it.map { entity -> entity.toCoin() }
            }
    }

    override fun containsFavoriteCoin(coinId: String): Flow<Int> =
        localDataSource.containsFavoriteCoin(coinId)

    override suspend fun insertFavoriteCoin(coin: Coin) =
        localDataSource.insertFavoriteCoin(coin.toFavoriteCoinEntity())

    override suspend fun deleteFavoriteCoin(coin: Coin) =
        localDataSource.deleteFavoriteCoin(coin.toFavoriteCoinEntity())
}
