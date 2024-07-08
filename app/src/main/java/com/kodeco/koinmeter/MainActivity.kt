package com.kodeco.koinmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.kodeco.koinmeter.data.network.RemoteApiService
import com.kodeco.koinmeter.domain.features.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.ui.components.LineChart
import com.kodeco.koinmeter.ui.theme.KoinMeterTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var coins by mutableStateOf(emptyList<Coin>())
        var chartData by mutableStateOf(emptyList<CoinMarketChartPrice>())

        val apiService: RemoteApiService by inject()
        val topCoinUseCase: GetTopCoinsUseCase by inject()

        lifecycleScope.launch {
            coins = topCoinUseCase(timeframe = TimeFrame.Day.value.strValue)
            val chartDataResponse = apiService.getCoinMarketChartData(coinId = "bitcoin", days = TimeFrame.Year.value.intValue)

            if (chartDataResponse.isSuccessful) {
                chartData = chartDataResponse.body() ?: emptyList()
            }
        }

        setContent {
            KoinMeterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        LazyColumn {
                            item {
                                LineChart(
                                    marketData = chartData,
                                    graphColor = Color.Red,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp)
                                        .padding(16.dp),
                                )
                            }

                            items(coins) {
                                Text(text = it.name ?: "N/A", modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
