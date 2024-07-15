package com.kodeco.koinmeter.presentation

import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.mocks.expectedCoinList
import com.kodeco.koinmeter.presentation.screens.topcoins.TopCoinsIntent
import com.kodeco.koinmeter.presentation.screens.topcoins.TopCoinsViewModel
import com.kodeco.koinmeter.presentation.screens.topcoins.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull


class TopCoinsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    lateinit var getTopCoinsUseCase: GetTopCoinsUseCase

    @RelaxedMockK
    lateinit var timeFrameSettingsUseCase: GetTimeFrameSettingsUseCase

    private lateinit var sut: TopCoinsViewModel

    private val expected = expectedCoinList

    @Before
    fun setUp() = runTest {
        MockKAnnotations.init(this)

        getTopCoinsUseCase = mockk()
        timeFrameSettingsUseCase = mockk()

        coEvery { timeFrameSettingsUseCase() } returns flowOf(TimeFrame.Day)
        coEvery { getTopCoinsUseCase(any()) } returns flowOf(expected)

        sut = TopCoinsViewModel(getTopCoinsUseCase, timeFrameSettingsUseCase)
    }

    @Test
    fun `LoadTopCoins intent should update uiState to Success when fetch is successful`() =
        runTest {
            val spyViewModel = spyk(sut)
            spyViewModel.processIntent(TopCoinsIntent.LoadTopCoins(TimeFrame.Day))

            val latestUIState = sut.uiState.value

            coVerify { getTopCoinsUseCase(TimeFrame.Day.value.range) }
            assertNotNull(latestUIState)

            assertTrue(latestUIState is UiState.Success)
            assertEquals((latestUIState as UiState.Success).coinList, expected)
        }
}
