package com.kodeco.koinmeter.presentation.screens.coindetails

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.koinmeter.presentation.components.CoinDetail
import com.kodeco.koinmeter.presentation.components.appbars.CoinDetailAppBar
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinDetailScreen(
    coinId: String,
    onBackClicked: () -> Unit,
    viewModel: CoinDetailViewModel = koinViewModel(),
) {
    viewModel.loadData(coinId)

    BackHandler {
        onBackClicked()
    }

    val uiState by viewModel.uiState.collectAsState()

    CoinDetail(
        uiState = uiState,
        appBar = {
            CoinDetailAppBar(
                uiState = uiState,
                onBackClicked = onBackClicked,
                addFavoriteCoin = { coin ->
                    viewModel.processIntent(CoinDetailIntent.AddFavoriteCoin(coin))
                },
                deleteFavoriteCoin = { coin ->
                    viewModel.processIntent(CoinDetailIntent.DeleteFavoriteCoin(coin))
                }
            )
        }
    )
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
