package com.kodeco.koinmeter.domain.usecase.settings

import com.kodeco.koinmeter.data.prefs.TimeFramePrefs

class GetTimeFrameSettingsUseCase(
    private val timeFramePrefs: TimeFramePrefs
) {
    operator fun invoke() = timeFramePrefs.getTimeFrameFlow()
}
