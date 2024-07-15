package com.kodeco.koinmeter.data.repository

import com.kodeco.koinmeter.data.local.datasource.favoritecoins.FavoriteCoinsLocalDataSource
import com.kodeco.koinmeter.data.mapper.toCoin
import com.kodeco.koinmeter.mocks.expectedCoinListEntity
import com.kodeco.koinmeter.mocks.expectedFavoriteCoinListEntity
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FavoriteCoinsRepositoryImplTest {

    private val localDataSource = mockk<FavoriteCoinsLocalDataSource>(relaxed = true)
    private val sut = FavoriteCoinsRepositoryImpl(localDataSource)

    @Test
    fun `getAllFavoriteCoins should return favorite coins from local data source`() = runTest {
        coEvery { localDataSource.getAllFavoriteCoins() } returns flowOf(
            expectedFavoriteCoinListEntity
        )

        sut.getAllFavoriteCoins().collect { coins ->
            assertEquals(expectedCoinListEntity.size, coins.size)
            coVerify { localDataSource.getAllFavoriteCoins() }
        }
    }

    @Test
    fun `containsFavoriteCoin should return true if coin is in favorite coins`() = runTest {
        coEvery { localDataSource.containsFavoriteCoin(any()) } returns flowOf(1)

        sut.containsFavoriteCoin("bitcoin").collect { contains ->
            assertEquals(1, contains)
            coVerify { localDataSource.containsFavoriteCoin("bitcoin") }
        }
    }

    @Test
    fun `insertFavoriteCoin should insert favorite coin into local data source`() = runTest {
        coEvery { localDataSource.insertFavoriteCoin(any()) } just Runs

        sut.insertFavoriteCoin(expectedFavoriteCoinListEntity[0].toCoin())
        coVerify { localDataSource.insertFavoriteCoin(expectedFavoriteCoinListEntity[0]) }
    }
}
