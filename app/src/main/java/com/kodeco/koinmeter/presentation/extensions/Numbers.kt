package com.kodeco.koinmeter.presentation.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double?.formatAsCurrency(): String {
    if (this == null) return "N/A"
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}

fun Long?.formatAsCurrency(): String {
    if (this == null) return "N/A"
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}

fun Double?.formatWithPattern(): String{
    if (this == null) return "N/A"
    val decimalFormat = DecimalFormat("#,###,###.###")
    return decimalFormat.format(this)
}
