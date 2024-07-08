package com.kodeco.koinmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.koinmeter.data.model.Coin
import com.kodeco.koinmeter.data.model.CoinMarket
import com.kodeco.koinmeter.data.model.CoinMarketChartPrice
import com.kodeco.koinmeter.data.model.TimeFrame
import com.kodeco.koinmeter.ui.components.LineChart
import com.kodeco.koinmeter.ui.theme.KoinMeterTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var coins by mutableStateOf(emptyList<Coin>())
        var coinDetails by mutableStateOf<CoinMarket?>(null)
        var chartData by mutableStateOf(emptyList<CoinMarketChartPrice>())

        val apiService: com.kodeco.koinmeter.data.network.RemoteApiService by inject()

        lifecycleScope.launch {
            val coinListResponse = apiService.getTopCoins(percentageTimeframe = TimeFrame.Day.value.strValue)
            val coinDetailsResponse = apiService.getCoinMarket(coinId = "bitcoin")
            val chartDataResponse = apiService.getCoinMarketChartData(coinId = "bitcoin", days = TimeFrame.Year.value.intValue)

            if (coinListResponse.isSuccessful) {
                coins = coinListResponse.body() ?: emptyList()
            }

            if (coinDetailsResponse.isSuccessful) {
                coinDetails = coinDetailsResponse.body()
            }

            if (chartDataResponse.isSuccessful) {
                chartData = chartDataResponse.body() ?: emptyList()
            }
        }

        setContent {
            KoinMeterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Column {
                            coinDetails?.let {
                                Text(text = it.id, modifier = Modifier.padding(8.dp))
                                Text(text = it.name ?: "N/A", modifier = Modifier.padding(8.dp))
                                Text(text = it.symbol ?: "N/A", modifier = Modifier.padding(8.dp))
                                it.image?.small?.let { img ->
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(img)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(100.dp)
                                            .padding(8.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Text(
                                    text = it.currentPrice?.toString() ?: "N/A",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }

                            LineChart(
                                marketData = chartData,
                                graphColor = androidx.compose.ui.graphics.Color.Red,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                            )
//                            chartData.firstOrNull()?.let { item ->
//                                Text(
//                                    text = "${item.date} - ${item.price} usd",
//                                    modifier = Modifier.padding(8.dp)
//                                )
//                            }
                        }

//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        LazyColumn {
//                            items(coins) {
//                                Text(text = it.name ?: "N/A", modifier = Modifier.padding(8.dp))
//                            }
//                        }
                    }
                }
            }
        }
    }
}
