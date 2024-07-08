package com.kodeco.koinmeter.domain.model

enum class TimeFrame(val value: Frame) {
    Day(Frame("24h", 1)),
    Week(Frame("7d", 7)),
    Month(Frame("30d", 30)),
    SixMonth(Frame("200d", 200)),
    Year(Frame("1y", 365))
}

data class Frame(
    val strValue: String,
    val intValue: Int
)
