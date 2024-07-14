package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSource
import com.kodeco.koinmeter.data.local.entities.CoinMarketChartEntity
import com.kodeco.koinmeter.data.mapper.toCoinMarketChartPrice
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.mocks.expectedCoinMarketChartPriceList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

class CoinMarketChartRepositoryImplTest {

    private val remoteDataSource = mockk<CoinMarketChartRemoteDataSource>(relaxed = true)

    private val localDataSource = mockk<CoinMarketChartLocalDataSource>(relaxed = true)

    private val sut = CoinMarketChartRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `getCoinMarketChartData should fetch coin market chart data from remote data source and insert it into local data source`() = runTest {
        val coinId = "bitcoin"
        val days = 1

        mockkStatic("com.kodeco.koinmeter.data.mapper.ExtensionsKt")
        coEvery { any<CoinMarketChartEntity>().toCoinMarketChartPrice() } returns expectedCoinMarketChartPriceList

        coEvery { remoteDataSource.getCoinMarketChartData(any(), any()) } answers {
            Response.success(expectedCoinMarketChartPriceList)
        }

        val result = sut.getCoinMarketChartData(coinId, days)
        assertEquals(expectedCoinMarketChartPriceList, result)

        coVerify { remoteDataSource.getCoinMarketChartData(coinId, days) }
        coVerify { localDataSource.insertCoinMarketChart(any()) }
    }

    @Test
    fun `deleteAllCoinMarketCharts should delete all coin market charts from local data source`() = runTest {
        sut.deleteAllCoinMarketCharts()
        coVerify { localDataSource.deleteAllCoinMarketCharts() }
    }
}
