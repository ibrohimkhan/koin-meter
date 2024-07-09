package com.kodeco.koinmeter.domain.usecase.topcoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository

class GetTopCoinsUseCase(private val repository: TopCoinsRepository) {

    suspend operator fun invoke(timeframe: String): List<Coin> =
        repository.getTopCoins(timeframe)
}
