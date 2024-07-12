package com.kodeco.koinmeter.presentation.screens.favoritecoins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.usecase.favoritecoins.GetFavoriteCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


// State
data class UiState(
    val coinList: List<Coin> = emptyList(),
    val error: Throwable? = null,
    val loading: Boolean = true,
)

// Intent
sealed class FavoriteCoinsIntent {
    data object LoadAllFavoriteCoins : FavoriteCoinsIntent()
}

class FavoriteCoinViewModel(
    private val getFavoriteCoinsUseCase: GetFavoriteCoinsUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        processIntent(FavoriteCoinsIntent.LoadAllFavoriteCoins)
    }

    fun processIntent(intent: FavoriteCoinsIntent) {
        viewModelScope.launch {
            when (intent) {
                is FavoriteCoinsIntent.LoadAllFavoriteCoins -> loadFavoriteCoins()
            }
        }
    }

    private suspend fun loadFavoriteCoins() {
        getFavoriteCoinsUseCase()
            .catch {
                _uiState.value = _uiState.value.copy(error = it, loading = false)
            }
            .collect {
                _uiState.value = _uiState.value.copy(coinList = it, loading = false)
            }
    }
}
