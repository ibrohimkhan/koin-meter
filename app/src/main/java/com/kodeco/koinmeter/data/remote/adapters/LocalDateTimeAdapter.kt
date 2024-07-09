package com.kodeco.koinmeter.data.remote.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeAdapter {
    @FromJson
    fun fromJson(timestamp: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
    }

    @ToJson
    fun toJson(value: LocalDateTime): Long {
        return value.toInstant(ZoneOffset.UTC).toEpochMilli()
    }
}
