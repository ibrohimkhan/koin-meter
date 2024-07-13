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
data class UiState(
    val coin: Coin? = null,
    val coinMarketChart: List<CoinMarketChartPrice> = emptyList(),
    var isFavorite: Boolean = false,
    val timeFrame: TimeFrame = TimeFrame.Day,
    val error: Throwable? = null,
)

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
    private val getTimeFrameSettingsUseCase: GetTimeFrameSettingsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val uiState = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getTimeFrameSettingsUseCase().collect { timeFrame ->
                _state.value = _state.value.copy(timeFrame = timeFrame)
            }
        }
    }

    fun loadData(coinId: String) {
        processIntent(CoinDetailIntent.LoadCoin(coinId))
        processIntent(CoinDetailIntent.LoadChart(coinId, _state.value.timeFrame))
        processIntent(CoinDetailIntent.IsFavoriteCoin(coinId))
    }

    fun processIntent(intent: CoinDetailIntent) {
        viewModelScope.launch {
            when (intent) {
                is CoinDetailIntent.LoadCoin -> getCoin(intent.coinId)
                is CoinDetailIntent.LoadChart -> getCoinMarketChart(intent.coinId, intent.timeFrame)
                is CoinDetailIntent.IsFavoriteCoin -> isFavoriteCoin(intent.coinId)
                is CoinDetailIntent.AddFavoriteCoin -> addFavoriteCoin(intent.coin)
                is CoinDetailIntent.DeleteFavoriteCoin -> deleteFavoriteCoin(intent.coin)
            }
        }
    }

    private suspend fun addFavoriteCoin(coin: Coin) {
        addFavoriteCoinUseCase(coin)
        _state.value = _state.value.copy(isFavorite = true)
    }

    private suspend fun deleteFavoriteCoin(coin: Coin) {
        deleteFavoriteCoinUseCase(coin)
        _state.value = _state.value.copy(isFavorite = false)
    }

    private suspend fun isFavoriteCoin(coinId: String) {
        containsFavoriteCoinUseCase(coinId).collect { isFavorite ->
            _state.value = _state.value.copy(isFavorite = isFavorite)
        }
    }

    private suspend fun getCoin(coinId: String) {
        getCoinUseCase(coinId)
            .catch {
                _state.value = _state.value.copy(error = it)
            }
            .collect {
                _state.value = _state.value.copy(coin = it)
            }
    }

    private suspend fun getCoinMarketChart(coinId: String, timeFrame: TimeFrame) {
        try {
            val result = getCoinMarketChartUseCase(coinId, timeFrame.value.days)
            _state.value = _state.value.copy(coinMarketChart = result)

        } catch (e: Throwable) {
            _state.value = _state.value.copy(error = e)
        }
    }
}
