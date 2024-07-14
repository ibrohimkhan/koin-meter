package com.kodeco.koinmeter.data.adapters

import com.kodeco.koinmeter.data.remote.adapters.LocalDateTimeAdapter
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapterTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single { LocalDateTimeAdapter() }
                single { LocalDateTime.now() }
            }
        )
    }

    @Test
    fun `fromJson should convert Long to LocalDateTime`() {
        val adapter = get<LocalDateTimeAdapter>()
        val expected = get<LocalDateTime>()

        val actual = adapter.fromJson(expected.toEpochSecond(ZoneOffset.UTC) * 1000)
        assertEquals(
            expected.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
            actual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        )
    }

    @Test
    fun `toJson should convert LocalDateTime to Long`() {
        val adapter = get<LocalDateTimeAdapter>()
        val expected = LocalDateTime.of(2024, 1, 1, 0, 0, 0)

        val actual = adapter.toJson(expected)
        assertEquals(expected.toEpochSecond(ZoneOffset.UTC) * 1000, actual)
    }
}
