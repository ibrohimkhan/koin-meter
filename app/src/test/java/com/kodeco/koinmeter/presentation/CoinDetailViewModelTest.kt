package com.kodeco.koinmeter.presentation

import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.GetCoinMarketChartUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.AddFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.ContainsFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.DeleteFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetCoinUseCase
import com.kodeco.koinmeter.mocks.expectedCoinBtc
import com.kodeco.koinmeter.mocks.expectedCoinMarketChartPriceBtc
import com.kodeco.koinmeter.presentation.screens.coindetails.CoinDetailIntent
import com.kodeco.koinmeter.presentation.screens.coindetails.CoinDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var getCoinUseCase: GetCoinUseCase

    @MockK
    lateinit var getCoinMarketChartUseCase: GetCoinMarketChartUseCase

    @MockK
    lateinit var addFavoriteCoinUseCase: AddFavoriteCoinUseCase

    @MockK
    lateinit var deleteFavoriteCoinUseCase: DeleteFavoriteCoinUseCase

    @MockK
    lateinit var containsFavoriteCoinUseCase: ContainsFavoriteCoinUseCase

    @MockK
    lateinit var getTimeFrameSettingsUseCase: GetTimeFrameSettingsUseCase

    private lateinit var sut: CoinDetailViewModel

    private val expectedTimeFrame = TimeFrame.Day
    private val expectedCoinId = "bitcoin"
    private val expected = expectedCoinBtc
    private val expectedCoinMarketChartPriceList = listOf(expectedCoinMarketChartPriceBtc)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getCoinUseCase = mockk()
        getCoinMarketChartUseCase = mockk()
        addFavoriteCoinUseCase = mockk()
        deleteFavoriteCoinUseCase = mockk()
        containsFavoriteCoinUseCase = mockk()

        coEvery { getCoinUseCase(any()) } returns flowOf(expected)
        coEvery { getCoinMarketChartUseCase(any(), any()) } returns expectedCoinMarketChartPriceList

        coEvery { addFavoriteCoinUseCase(any()) } returns Unit
        coEvery { deleteFavoriteCoinUseCase(any()) } returns Unit
        coEvery { containsFavoriteCoinUseCase(any()) } returns flowOf(true)

        coEvery { getTimeFrameSettingsUseCase() } returns flowOf(expectedTimeFrame)

        sut = CoinDetailViewModel(
            getCoinUseCase,
            getCoinMarketChartUseCase,
            addFavoriteCoinUseCase,
            deleteFavoriteCoinUseCase,
            containsFavoriteCoinUseCase,
            getTimeFrameSettingsUseCase
        )
    }

    @Test
    fun `LoadCoin intent should update uiState to Success when fetch is successful`() = runTest {
        sut.processIntent(CoinDetailIntent.LoadCoin(expectedCoinId))

        val latestUIState = sut.uiState.value

        coVerify { getCoinUseCase(expectedCoinId) }
        assertNotNull(latestUIState)
        assertEquals(latestUIState.coin, expected)
    }

    @Test
    fun `LoadChart intent should update uiState to Success when fetch is successful`() = runTest {
        sut.processIntent(CoinDetailIntent.LoadChart(expectedCoinId, expectedTimeFrame))

        val latestUIState = sut.uiState.value

        coVerify { getCoinMarketChartUseCase(expectedCoinId, expectedTimeFrame.value.days) }
        assertNotNull(latestUIState)
        assertEquals(latestUIState.coinMarketChart, expectedCoinMarketChartPriceList)
    }

    @Test
    fun `IsFavoriteCoin intent should check if the coin is favorite or not`() = runTest {
        sut.processIntent(CoinDetailIntent.IsFavoriteCoin(expectedCoinId))

        val latestUIState = sut.uiState.value

        coVerify { containsFavoriteCoinUseCase(expectedCoinId) }
        assertNotNull(latestUIState)
        assertEquals(latestUIState.isFavorite, true)
    }

    @Test
    fun `AddFavoriteCoin intent should update uiState to favorite and call addFavoriteCoinUseCase`() = runTest {
        sut.processIntent(CoinDetailIntent.AddFavoriteCoin(expected))

        val latestUIState = sut.uiState.value

        coVerify { addFavoriteCoinUseCase(expected) }
        assertNotNull(latestUIState)
        assertEquals(latestUIState.isFavorite, true)
    }

    @Test
    fun `DeleteFavoriteCoin intent should update uiState to not favorite and call deleteFavoriteCoinUseCase`() = runTest {
        sut.processIntent(CoinDetailIntent.DeleteFavoriteCoin(expected))

        val latestUIState = sut.uiState.value

        coVerify { deleteFavoriteCoinUseCase(expected) }
        assertNotNull(latestUIState)
        assertEquals(latestUIState.isFavorite, false)
    }
}
