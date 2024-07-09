package com.kodeco.koinmeter.domain.usecase.favoritecoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteCoinsUseCase(
    private val repository: TopCoinsRepository
) {
    operator fun invoke(): Flow<List<Coin>> = repository.getFavoriteCoins()
}
