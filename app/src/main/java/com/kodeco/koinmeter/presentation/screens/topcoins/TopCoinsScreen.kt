package com.kodeco.koinmeter.presentation.screens.topcoins

import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.presentation.components.CoinList
import com.kodeco.koinmeter.presentation.components.Errors
import com.kodeco.koinmeter.presentation.components.Loading
import com.kodeco.koinmeter.presentation.components.TopCoinsAppBar
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun TopCoinsScreen(
    onItemClicked: (String) -> Unit,
    viewModel: TopCoinsViewModel = koinViewModel(),
) {
    val errorMessage = stringResource(R.string.something_went_wrong)

    val timeSettings = viewModel.timeFrameSettings.collectAsState(initial = TimeFrame.Day)
    val uiState by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState is UiState.Loading,
        onRefresh = {
            viewModel.processIntent(TopCoinsIntent.LoadTopCoins(timeSettings.value))
        }
    )

    when (uiState) {
        is UiState.Loading -> Loading()

        is UiState.Error -> Errors(
            message = (uiState as UiState.Error).throwable.message ?: errorMessage,
            onRetry = {
                viewModel.processIntent(TopCoinsIntent.LoadTopCoins(timeSettings.value))
            }
        )

        is UiState.Success -> CoinList(
            coins = (uiState as UiState.Success).coinList,
            appBar = { TopCoinsAppBar() },
            onItemClicked = onItemClicked,
            pullRefreshState = pullRefreshState,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopCoinsPreview() {
    KoinMeterTheme {
        TopCoinsScreen(
            onItemClicked = {}
        )
    }
}
