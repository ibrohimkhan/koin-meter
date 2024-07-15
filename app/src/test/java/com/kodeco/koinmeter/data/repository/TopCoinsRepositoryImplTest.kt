package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.favoritecoins.FavoriteCoinsLocalDataSource
import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSource
import com.kodeco.koinmeter.data.mapper.toCoin
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.mocks.expectedCoinBtcEntity
import com.kodeco.koinmeter.mocks.expectedCoinList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class TopCoinsRepositoryImplTest {

    private val remoteDataSource = mockk<TopCoinsRemoteDataSource>(relaxed = true)

    private val localDataSource = mockk<TopCoinsLocalDataSource>(relaxed = true)

    private val favoriteCoinsLocalDataSource = mockk<FavoriteCoinsLocalDataSource>(relaxed = true)

    private val sut = TopCoinsRepositoryImpl(remoteDataSource, localDataSource, favoriteCoinsLocalDataSource)

    private val timeFrame = TimeFrame.Day

    @Test
    fun `getTopCoins should fetch top coins from remote data source and insert them into local data source`() = runTest {
        coEvery { remoteDataSource.getTopCoins(timeFrame.value.range) } answers {
            Response.success(expectedCoinList)
        }

        sut.getTopCoins(timeFrame.value.range).collect { coins ->
            assertEquals(expectedCoinList.size, coins.size)

            coVerify { remoteDataSource.getTopCoins(timeFrame.value.range) }
            coVerify { localDataSource.insertCoins(any()) }
            coVerify { favoriteCoinsLocalDataSource.insertFavoriteCoins(any()) }
        }
    }

    @Test
    fun `getCoin should return coin from local data source if it exists`() = runTest {
        coEvery { localDataSource.getCoinById(any()) } returns expectedCoinBtcEntity

        sut.getCoin("bitcoin").collect { coin ->
            assertEquals(expectedCoinBtcEntity.toCoin(), coin)
            coVerify { localDataSource.getCoinById("bitcoin") }
        }
    }
}
