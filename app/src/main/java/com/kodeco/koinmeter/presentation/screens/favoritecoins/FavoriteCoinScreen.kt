package com.kodeco.koinmeter.presentation.screens.favoritecoins

import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.presentation.components.CoinList
import com.kodeco.koinmeter.presentation.components.Errors
import com.kodeco.koinmeter.presentation.components.Loading
import com.kodeco.koinmeter.presentation.components.appbars.FavoriteCoinsAppBar
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteCoinScreen(
    onItemClicked: (String) -> Unit,
    viewModel: FavoriteCoinViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.loading -> Loading()
        uiState.error != null -> Errors(
            message = uiState.error?.message ?: stringResource(R.string.something_went_wrong)
        )
        else -> CoinList(
            coins = uiState.coinList,
            appBar = { FavoriteCoinsAppBar() },
            onItemClicked = onItemClicked,
            pullRefreshState = rememberPullRefreshState(
                refreshing = false,
                onRefresh = {}
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    KoinMeterTheme {
        FavoriteCoinScreen(
            onItemClicked = {}
        )
    }
}
