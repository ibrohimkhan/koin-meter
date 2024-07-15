package com.kodeco.koinmeter.presentation.screens.topcoins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

// State
sealed class UiState {
    data class Success(val coinList: List<Coin>) : UiState()

    data class Error(val throwable: Throwable) : UiState()

    data object Loading : UiState()
}

// Intent
sealed class TopCoinsIntent {
    data class LoadTopCoins(val timeFrame: TimeFrame) : TopCoinsIntent()
}

class TopCoinsViewModel(
    private val getTopCoinsUseCase: GetTopCoinsUseCase,
    getTimeFrameSettingsUseCase: GetTimeFrameSettingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    val timeFrameSettings = getTimeFrameSettingsUseCase()

    init {
        viewModelScope.launch {
            timeFrameSettings.collect {
                fetchTopCoins(it.value.range)
            }
        }
    }

    fun processIntent(intent: TopCoinsIntent) {
        viewModelScope.launch {
            when (intent) {
                is TopCoinsIntent.LoadTopCoins -> fetchTopCoins(intent.timeFrame.value.range)
            }
        }
    }

    private suspend fun fetchTopCoins(range: String) {
        _uiState.value = UiState.Loading
        getTopCoinsUseCase(range)
            .catch {
                _uiState.value = UiState.Error(it)
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }
}
