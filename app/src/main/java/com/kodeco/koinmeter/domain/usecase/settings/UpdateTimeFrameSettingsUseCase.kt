package com.kodeco.koinmeter.domain.usecase.settings

import com.kodeco.koinmeter.data.prefs.TimeFramePrefs
import com.kodeco.koinmeter.domain.model.TimeFrame

class UpdateTimeFrameSettingsUseCase(
    private val prefs: TimeFramePrefs
) {
    suspend operator fun invoke(timeFrame: TimeFrame) {
        prefs.saveTimeFrame(timeFrame)
    }
}
