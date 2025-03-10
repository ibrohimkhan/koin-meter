package com.kodeco.koinmeter.domain.usecase.favoritecoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.FavoriteCoinsRepository

class DeleteFavoriteCoinUseCase(
    private val repository: FavoriteCoinsRepository
) {
    suspend operator fun invoke(coin: Coin) = repository.deleteFavoriteCoin(coin)
}
