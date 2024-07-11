package com.kodeco.koinmeter.presentation.screens.coindetails

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.presentation.components.CoinDetail
import com.kodeco.koinmeter.presentation.components.CoinDetailAppBar
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinDetailScreen(
    coinId: String,
    onBackClicked: () -> Unit,
    viewModel: CoinDetailViewModel = koinViewModel(),
) {
    BackHandler {
        onBackClicked()
    }

    var coin by rememberSaveable { mutableStateOf<Coin?>(null) }
    var coinMarketChart by rememberSaveable { mutableStateOf<List<CoinMarketChartPrice>?>(null) }
    var isFavoriteCoin by rememberSaveable { mutableStateOf(false) }

    var coinError by rememberSaveable { mutableStateOf<Throwable?>(null) }
    var coinMarketChartError by rememberSaveable { mutableStateOf<Throwable?>(null) }

    val timeSettings by viewModel.timeFrameSettings.collectAsState(initial = TimeFrame.Day)
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UiState.Initial -> {
            viewModel.processIntent(CoinDetailIntent.IsFavoriteCoin(coinId))
            viewModel.processIntent(CoinDetailIntent.LoadCoin(coinId))
            viewModel.processIntent(CoinDetailIntent.LoadChart(coinId, timeSettings))
        }

        is UiState.Loading -> Unit //Loading()

        is UiState.SuccessOnChart -> coinMarketChart =
            (uiState as UiState.SuccessOnChart).coinMarketChart

        is UiState.SuccessOnCoin -> coin =
            (uiState as UiState.SuccessOnCoin).coin

        is UiState.IsFavoriteCoin -> isFavoriteCoin =
            (uiState as UiState.IsFavoriteCoin).isFavorite

        is UiState.CoinError -> coinError =
            (uiState as UiState.CoinError).throwable

        is UiState.CoinMarketChartError -> coinMarketChartError =
            (uiState as UiState.CoinMarketChartError).throwable
    }

    coin?.let {
        CoinDetail(
            coin = it,
            coinMarketChart = coinMarketChart,
            timeSettings = timeSettings,
            appBar = {
                CoinDetailAppBar(
                    coin = it,
                    isFavorite = isFavoriteCoin,
                    onBackClicked = onBackClicked,
                    addFavoriteCoin = { coin ->
                        viewModel.processIntent(CoinDetailIntent.AddFavoriteCoin(coin))
                    },
                    deleteFavoriteCoin = { coin ->
                        viewModel.processIntent(CoinDetailIntent.DeleteFavoriteCoin(coin))
                    }
                )
            },
            coinError = coinError,
            coinMarketChartError = coinMarketChartError
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoinDetailScreenPreview() {
    KoinMeterTheme {
        CoinDetailScreen(
            coinId = "bitcoin",
            onBackClicked = {}
        )
    }
}
