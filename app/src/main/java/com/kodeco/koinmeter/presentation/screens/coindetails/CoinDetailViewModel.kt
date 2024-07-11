package com.kodeco.koinmeter.presentation.screens.coindetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.GetCoinMarketChartUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.AddFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.ContainsFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.DeleteFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetCoinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


// State
sealed class UiState {
    data class SuccessOnCoin(val coin: Coin?) : UiState()

    data class SuccessOnChart(val coinMarketChart: List<CoinMarketChartPrice>) : UiState()

    data class CoinError(val throwable: Throwable) : UiState()

    data class CoinMarketChartError(val throwable: Throwable) : UiState()

    data class IsFavoriteCoin(val isFavorite: Boolean) : UiState()

    data object Loading : UiState()

    data object Initial : UiState()
}

// Intent
sealed class CoinDetailIntent {
    data class LoadCoin(val coinId: String) : CoinDetailIntent()
    data class LoadChart(val coinId: String, val timeFrame: TimeFrame) : CoinDetailIntent()
    data class IsFavoriteCoin(val coinId: String) : CoinDetailIntent()
    data class AddFavoriteCoin(val coin: Coin) : CoinDetailIntent()
    data class DeleteFavoriteCoin(val coin: Coin) : CoinDetailIntent()
}

class CoinDetailViewModel(
    private val getCoinUseCase: GetCoinUseCase,
    private val getCoinMarketChartUseCase: GetCoinMarketChartUseCase,
    private val addFavoriteCoinUseCase: AddFavoriteCoinUseCase,
    private val deleteFavoriteCoinUseCase: DeleteFavoriteCoinUseCase,
    private val containsFavoriteCoinUseCase: ContainsFavoriteCoinUseCase,
    getTimeFrameSettingsUseCase: GetTimeFrameSettingsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState = _uiState.asStateFlow()

    val timeFrameSettings = getTimeFrameSettingsUseCase()

    fun processIntent(intent: CoinDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is CoinDetailIntent.LoadCoin -> getCoin(intent.coinId)
                is CoinDetailIntent.LoadChart -> getCoinMarketChart(intent.coinId, intent.timeFrame)
                is CoinDetailIntent.IsFavoriteCoin -> isFavoriteCoin(intent.coinId)
                is CoinDetailIntent.AddFavoriteCoin -> addFavoriteCoinUseCase(intent.coin)
                is CoinDetailIntent.DeleteFavoriteCoin -> deleteFavoriteCoinUseCase(intent.coin)
            }
        }
    }

    private suspend fun isFavoriteCoin(coinId: String) {
        containsFavoriteCoinUseCase(coinId).collect { isFavorite ->
            _uiState.value = UiState.IsFavoriteCoin(isFavorite)
        }
    }

    private suspend fun getCoin(coinId: String) {
        _uiState.value = UiState.Loading

        getCoinUseCase(coinId)
            .catch {
                _uiState.value = UiState.CoinError(it)
            }
            .collect {
                _uiState.value = UiState.SuccessOnCoin(it)
            }
    }

    private suspend fun getCoinMarketChart(coinId: String, timeFrame: TimeFrame) {
        _uiState.value = UiState.Loading

        try {
            val result = getCoinMarketChartUseCase(coinId, timeFrame.value.days)
            _uiState.value = UiState.SuccessOnChart(result)

        } catch (e: Exception) {
            _uiState.value = UiState.CoinMarketChartError(e)
        }
    }
}
