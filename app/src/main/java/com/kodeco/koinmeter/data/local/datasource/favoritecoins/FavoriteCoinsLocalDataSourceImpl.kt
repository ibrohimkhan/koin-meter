package com.kodeco.koinmeter.data.local.datasource.favoritecoins

import com.kodeco.koinmeter.data.local.dao.FavoriteCoinDao
import com.kodeco.koinmeter.data.local.entities.FavoriteCoinEntity
import kotlinx.coroutines.flow.Flow

class FavoriteCoinsLocalDataSourceImpl(
    private val favoriteCoinDao: FavoriteCoinDao
) : FavoriteCoinsLocalDataSource {

    override fun getAllFavoriteCoins(): Flow<List<FavoriteCoinEntity>> =
        favoriteCoinDao.getAllFavoriteCoins()

    override fun containsFavoriteCoin(coinId: String): Flow<Int> =
        favoriteCoinDao.containsFavoriteCoin(coinId)

    override suspend fun insertFavoriteCoin(favoriteCoin: FavoriteCoinEntity) =
        favoriteCoinDao.insertFavoriteCoin(favoriteCoin)

    override suspend fun deleteFavoriteCoin(favoriteCoin: FavoriteCoinEntity) =
        favoriteCoinDao.deleteFavoriteCoin(favoriteCoin)
}
