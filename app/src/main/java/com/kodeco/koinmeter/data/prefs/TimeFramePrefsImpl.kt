package com.kodeco.koinmeter.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kodeco.koinmeter.domain.model.Frame
import com.kodeco.koinmeter.domain.model.TimeFrame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private const val STORE_NAME = "timeframe_prefs"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)

object PrefsKeys {
    val DAYS_KEY = intPreferencesKey("days_key")
    val RANGE_KEY = stringPreferencesKey("range_key")
}

class TimeFramePrefsImpl(private val context: Context) : TimeFramePrefs {

    override fun getTimeFrameFlow(): Flow<TimeFrame> = context.dataStore.data.map {
        val days = it[PrefsKeys.DAYS_KEY] ?: 1
        val range = it[PrefsKeys.RANGE_KEY] ?: "24h"

        TimeFrame.entries.first { it.value == Frame(range, days) }
    }

    override suspend fun saveTimeFrame(timeFrame: TimeFrame) {
        context.dataStore.edit { preferences ->
            preferences[PrefsKeys.DAYS_KEY] = timeFrame.value.days
            preferences[PrefsKeys.RANGE_KEY] = timeFrame.value.range
        }
    }
}
