package com.kodeco.koinmeter.domain.features.topcoins

import com.kodeco.koinmeter.domain.model.Coin

class GetTopCoinsUseCase(private val topCoinsRepository: TopCoinsRepository) {
    suspend operator fun invoke(timeframe: String): List<Coin> =
        topCoinsRepository.getTopCoins(timeframe)
}
