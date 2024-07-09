package com.kodeco.koinmeter.domain.usecase.topcoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository

class GetCoinUseCase(private val repository: TopCoinsRepository) {

    suspend operator fun invoke(coinId: String): Coin? =
        repository.getCoin(coinId)
}
