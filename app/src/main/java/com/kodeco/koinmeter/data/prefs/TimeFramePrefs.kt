package com.kodeco.koinmeter.data.prefs

import com.kodeco.koinmeter.domain.model.TimeFrame
import kotlinx.coroutines.flow.Flow

interface TimeFramePrefs {
    fun getTimeFrameFlow(): Flow<TimeFrame>
    suspend fun saveTimeFrame(timeFrame: TimeFrame)
}
