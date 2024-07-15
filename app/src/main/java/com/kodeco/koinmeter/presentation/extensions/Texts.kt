package com.kodeco.koinmeter.presentation.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatDateChart(): String {
    val inputFormat = DateTimeFormatter.ISO_DATE_TIME
    val outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val localDateTime = LocalDateTime.parse(this.toString(), inputFormat)

    return outputFormat.format(localDateTime)
}

fun String.formatDate(): String {
    val inputFormat = DateTimeFormatter.ISO_DATE_TIME
    val outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    val localDateTime = LocalDateTime.parse(this, inputFormat)

    return outputFormat.format(localDateTime)
}
