package com.kodeco.koinmeter.domain.usecase.topcoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import kotlinx.coroutines.flow.Flow

class GetTopCoinsUseCase(private val repository: TopCoinsRepository) {

    operator fun invoke(timeframe: String): Flow<List<Coin>> =
        repository.getTopCoins(timeframe)
}
