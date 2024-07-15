package com.kodeco.koinmeter.domain.usecase.favoritecoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.FavoriteCoinsRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteCoinsUseCase(
    private val repository: FavoriteCoinsRepository
) {
    operator fun invoke(): Flow<List<Coin>> = repository.getAllFavoriteCoins()
}
