package com.kodeco.koinmeter.domain.usecase.favoritecoins

import com.kodeco.koinmeter.domain.repository.TopCoinsRepository

class UpdateFavoriteCoinUseCase(
    private val repository: TopCoinsRepository
) {
    suspend operator fun invoke(coinId: String, isFavorite: Boolean) =
        repository.updateFavoriteStatus(coinId, isFavorite)
}
