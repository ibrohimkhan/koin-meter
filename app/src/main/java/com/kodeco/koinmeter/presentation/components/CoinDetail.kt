package com.kodeco.koinmeter.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.presentation.extensions.formatAsCurrency
import com.kodeco.koinmeter.presentation.screens.coindetails.UiState
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import java.time.LocalDateTime


@Composable
fun CoinDetail(
    uiState: UiState,
    appBar: @Composable () -> Unit,
) {
    Scaffold(
        topBar = appBar,
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->

        val coin = uiState.coin ?: return@Scaffold

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                coin.image?.let {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .crossfade(true)
                            .build(),
                        contentDescription = coin.id,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(56.dp)
                            .height(56.dp)
                            .clip(shape = CircleShape)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = coin.name ?: stringResource(R.string.unknown),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = coin.symbol?.uppercase() ?: stringResource(R.string.unknown),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Text(
                text = coin.currentPrice.formatAsCurrency(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp)
            )

            LineChart(
                marketData = uiState.coinMarketChart,
                graphColor = when {
                    (coin.priceChangePercentage24h ?: 0.0) == 0.0 -> {
                        Color.Gray.copy(alpha = 0.3f)
                    }

                    (coin.priceChangePercentage24h ?: 0.0) > 0 -> {
                        Color.Green.copy(alpha = 0.3f)
                    }

                    else -> {
                        Color.Red.copy(alpha = 0.3f)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(vertical = 8.dp)
            )

            MarketData(coin = coin)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinDetailPreview() {
    KoinMeterTheme {
        CoinDetail(
            uiState = UiState(
                coin = Coin(
                    id = "bitcoin",
                    name = "Bitcoin",
                    symbol = "BTC",
                    image = "https://example.com/bitcoin.png",
                    currentPrice = 70000.0
                ),
                coinMarketChart = listOf(
                    CoinMarketChartPrice(
                        date = LocalDateTime.now(),
                        price = 70000.0
                    ),
                    CoinMarketChartPrice(
                        date = LocalDateTime.now(),
                        price = 60000.0
                    ),
                    CoinMarketChartPrice(
                        date = LocalDateTime.now(),
                        price = 80000.0
                    )
                ),
                isFavorite = true,
                timeFrame = TimeFrame.Day,
                error = null
            ),
            appBar = { FavoriteCoinsAppBar() },
        )
    }
}
