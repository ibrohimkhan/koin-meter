package com.kodeco.koinmeter.presentation.screens.settings

import androidx.lifecycle.ViewModel
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.settings.UpdateTimeFrameSettingsUseCase

class SettingsViewModel(
    private val getTimeFrameSettingsUseCase: GetTimeFrameSettingsUseCase,
    private val setTimeFrameSettingsUseCase: UpdateTimeFrameSettingsUseCase,
) : ViewModel() {
}
