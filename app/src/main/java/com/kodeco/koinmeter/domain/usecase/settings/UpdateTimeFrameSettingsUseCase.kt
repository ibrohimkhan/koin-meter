package com.kodeco.koinmeter.domain.usecase.settings

import com.kodeco.koinmeter.data.prefs.TimeFramePrefs
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.DeleteAllCoinMarketChartsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase

class UpdateTimeFrameSettingsUseCase(
    private val prefs: TimeFramePrefs,
    private val deleteAllCoinMarketChartsUseCase: DeleteAllCoinMarketChartsUseCase,
    private val getTopCoinsUseCase: GetTopCoinsUseCase

) {
    suspend operator fun invoke(timeFrame: TimeFrame) {
        prefs.saveTimeFrame(timeFrame)
        deleteAllCoinMarketChartsUseCase()
        getTopCoinsUseCase(timeFrame.value.range)
    }
}
