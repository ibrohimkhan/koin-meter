package com.kodeco.koinmeter.domain.usecase.favoritecoins

import com.kodeco.koinmeter.domain.repository.FavoriteCoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContainsFavoriteCoinUseCase(
    private val repository: FavoriteCoinsRepository
) {
    operator fun invoke(coinId: String): Flow<Boolean> =
        repository.containsFavoriteCoin(coinId).map { it > 0 }
}
