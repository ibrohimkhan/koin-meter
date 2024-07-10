package com.kodeco.koinmeter.domain.usecase.topcoins

import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoinUseCase(private val repository: TopCoinsRepository) {

    operator suspend fun invoke(coinId: String): Flow<Coin?> =
        repository.getCoin(coinId)
}
