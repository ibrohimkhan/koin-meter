package com.kodeco.koinmeter.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.settings.UpdateTimeFrameSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// State
data class UiState(
    val timeFrame: TimeFrame? = null,
)

// Intent
sealed class SettingsIntent {
    data object GetTimeFrame : SettingsIntent()
    data class SetTimeFrame(val timeFrame: TimeFrame) : SettingsIntent()
}

class SettingsViewModel(
    private val getTimeFrameSettingsUseCase: GetTimeFrameSettingsUseCase,
    private val setTimeFrameSettingsUseCase: UpdateTimeFrameSettingsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        processIntent(SettingsIntent.GetTimeFrame)
    }

    fun processIntent(intent: SettingsIntent) {
        viewModelScope.launch {
            when (intent) {
                is SettingsIntent.GetTimeFrame -> getTimeFrame()
                is SettingsIntent.SetTimeFrame -> setTimeFrame(intent.timeFrame)
            }
        }
    }

    private suspend fun setTimeFrame(timeFrame: TimeFrame) {
        setTimeFrameSettingsUseCase(timeFrame)
        _uiState.value = _uiState.value.copy(timeFrame = timeFrame)
    }

    private suspend fun getTimeFrame() {
        getTimeFrameSettingsUseCase().collect {
            _uiState.value = _uiState.value.copy(timeFrame = it)
        }
    }
}
