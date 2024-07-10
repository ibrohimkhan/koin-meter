package com.kodeco.koinmeter.domain.usecase.settings

import com.kodeco.koinmeter.data.prefs.TimeFramePrefs
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.DeleteAllCoinMarketChartsUseCase

class UpdateTimeFrameSettingsUseCase(
    private val prefs: TimeFramePrefs,
    private val deleteAllCoinMarketChartsUseCase: DeleteAllCoinMarketChartsUseCase,
) {
    suspend operator fun invoke(timeFrame: TimeFrame) {
        prefs.saveTimeFrame(timeFrame)
        deleteAllCoinMarketChartsUseCase()
    }
}
