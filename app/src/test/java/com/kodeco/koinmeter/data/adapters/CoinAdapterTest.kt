package com.kodeco.koinmeter.data.adapters

import com.kodeco.koinmeter.data.remote.adapters.CoinAdapter
import com.kodeco.koinmeter.mocks.expectedCoinDtoList
import com.kodeco.koinmeter.mocks.expectedCoinDtoListDomain
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import kotlin.test.assertEquals

class CoinAdapterTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single { CoinAdapter() }
            })
    }

    @Test
    fun `fromJson should convert list of CoinDto to list of Coin `() {
        val adapter = get<CoinAdapter>()
        val expected = expectedCoinDtoListDomain

        val actual = adapter.fromJson(expectedCoinDtoList)
        assertEquals(expected, actual)
    }
}