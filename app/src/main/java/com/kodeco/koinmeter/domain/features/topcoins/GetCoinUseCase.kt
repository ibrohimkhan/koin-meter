package com.kodeco.koinmeter.domain.features.topcoins

import com.kodeco.koinmeter.domain.model.Coin

class GetCoinUseCase(private val topCoinsRepository: TopCoinsRepository) {
    suspend operator fun invoke(coinId: String): Coin? =
        topCoinsRepository.getCoin(coinId)
}
