package com.kodeco.koinmeter.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme

@Composable
fun CoinList(
    coins: List<Coin>,
    appBar: @Composable () -> Unit,
    onItemClicked: (String) -> Unit,
    pullRefreshState: PullRefreshState,
) {
    Scaffold(
        topBar = appBar,
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn {
                items(coins) { coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClicked = onItemClicked,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListPreview() {
    KoinMeterTheme {
        CoinList(
            coins = listOf(
                Coin(id = "bitcoin", name = "Bitcoin", symbol = "BTC"),
                Coin(id = "eth", name = "Ethereum", symbol = "ETH"),
                Coin(id = "sol", name = "Solana", symbol = "SOL"),
                Coin(id = "dot", name = "Polkadot", symbol = "DOT"),
            ),
            appBar = { TopCoinsAppBar() },
            onItemClicked = {},
            pullRefreshState = rememberPullRefreshState(
                refreshing = false,
                onRefresh = {}
            ),
        )
    }
}
