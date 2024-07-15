package com.kodeco.koinmeter.presentation

import com.kodeco.koinmeter.domain.usecase.favoritecoins.GetFavoriteCoinsUseCase
import com.kodeco.koinmeter.mocks.expectedCoinList
import com.kodeco.koinmeter.presentation.screens.favoritecoins.FavoriteCoinViewModel
import com.kodeco.koinmeter.presentation.screens.favoritecoins.FavoriteCoinsIntent
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteCoinViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    lateinit var getFavoriteCoinsUseCase: GetFavoriteCoinsUseCase

    private lateinit var sut: FavoriteCoinViewModel

    private val expected = expectedCoinList

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getFavoriteCoinsUseCase = mockk()

        coEvery { getFavoriteCoinsUseCase() } returns flowOf(expected)

        sut = FavoriteCoinViewModel(getFavoriteCoinsUseCase)
    }

    @Test
    fun `LoadAllFavoriteCoins intent should update uiState to Success when fetch is successful`() = runTest {
        sut.processIntent(FavoriteCoinsIntent.LoadAllFavoriteCoins)

        val latestUIState = sut.uiState.value

        coVerify { getFavoriteCoinsUseCase() }
        assertNotNull(latestUIState)

        assertTrue(latestUIState.coinList.isNotEmpty())
        assertEquals(latestUIState.coinList, expected)
    }
}
