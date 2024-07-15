package com.kodeco.koinmeter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class CoinMarketChartPrice(
    val date: LocalDateTime,
    val price: Double
) : Parcelable
