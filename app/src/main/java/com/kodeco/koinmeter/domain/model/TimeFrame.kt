package com.kodeco.koinmeter.domain.model

enum class TimeFrame(val value: Frame) {
    Day(Frame("24h", 1, "24 Hours")),
    Week(Frame("7d", 7, "Week")),
    Month(Frame("30d", 30, "Month")),
    SixMonth(Frame("200d", 200, "Six Months")),
    Year(Frame("1y", 365, "Year"))
}

data class Frame(
    val range: String,
    val days: Int,
    val title: String
)
