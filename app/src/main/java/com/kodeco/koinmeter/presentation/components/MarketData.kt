package com.kodeco.koinmeter.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.presentation.extensions.formatAsCurrency
import com.kodeco.koinmeter.presentation.extensions.formatDate
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme

@Composable
fun MarketData(coin: Coin) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.market_data),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        MarketValues("Market Cap", coin.marketCap.formatAsCurrency())
        MarketValues("Trading Volume 24h", coin.totalVolume.formatAsCurrency())
        MarketValues("Highest Price 24h", coin.high24h.formatAsCurrency())
        MarketValues("Lowest Price 24h", coin.low24h.formatAsCurrency())
        MarketValues("Circulating Supply", coin.circulatingSupply.formatAsCurrency())
        MarketValues("Total Supply", coin.totalSupply.formatAsCurrency())
        MarketValues("Max Supply", coin.maxSupply.formatAsCurrency())
        MarketValues("All Time High Price", coin.ath.formatAsCurrency())
        MarketValues("All Time High Date", coin.athDate.orEmpty().formatDate())
        MarketValues("All Time Low Price", coin.atl.formatAsCurrency())
        MarketValues("All Time Low Date", coin.atlDate.orEmpty().formatDate())
        MarketValues("Last updated", coin.lastUpdated.orEmpty().formatDate())
    }
}

@Preview(showBackground = true)
@Composable
fun MarketDataPreview() {
    KoinMeterTheme {
        MarketData(
            coin = Coin(
                id = "bitcoin",
                name = "Bitcoin",
                symbol = "BTC",
                currentPrice = 70000.0,
                image = "https://example.com/bitcoin.png",
                marketCap = 1234567890,
                totalVolume = 1234567890.0,
                high24h = 80000.0,
                low24h = 60000.0,
                circulatingSupply = 1000000.0,
                totalSupply = 1000000.0,
                maxSupply = 1000000.0,
                ath = 1000000.0,
                athDate = "2023-01-01",
                atl = 100.0,
                atlDate = "2022-12-31",
                lastUpdated = "2023-01-01T00:00:00Z",
            )
        )
    }
}
