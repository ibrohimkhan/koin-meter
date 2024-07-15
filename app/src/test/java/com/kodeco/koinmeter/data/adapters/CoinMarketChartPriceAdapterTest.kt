package com.kodeco.koinmeter.data.adapters

import com.kodeco.koinmeter.data.remote.adapters.CoinMarketChartPriceAdapter
import com.kodeco.koinmeter.mocks.coinMarketChartDto
import com.kodeco.koinmeter.mocks.coinMarketChartDtoExpected
import com.kodeco.koinmeter.mocks.expectedCoinMarketChartPriceBtc
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get

class CoinMarketChartPriceAdapterTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single { CoinMarketChartPriceAdapter() }
            }
        )
    }

    @Test
    fun `fromJson should convert CoinChartDto to CoinMarketChartPrice`() {
        val adapter = get<CoinMarketChartPriceAdapter>()
        val expected = listOf(expectedCoinMarketChartPriceBtc)

        val actual = adapter.fromJson(coinMarketChartDto)
        assertEquals(expected, actual)
    }

    @Test
    fun `toJson should convert CoinMarketChartPrice to CoinChartDto`() {
        val adapter = get<CoinMarketChartPriceAdapter>()
        val expected = coinMarketChartDtoExpected

        val actual = adapter.toJson(listOf(expectedCoinMarketChartPriceBtc))
        assertEquals(expected, actual)
    }
}
