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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.GetCoinMarketChartUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.AddFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.GetFavoriteCoinsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetCoinUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.presentation.components.LineChart
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var chartData by mutableStateOf(emptyList<CoinMarketChartPrice>())
        var topCoins by mutableStateOf<Flow<List<Coin>>>(flowOf(emptyList()))
        var firstFavCoin by mutableStateOf<Flow<Coin?>>(flowOf(null))

        val coinUseCase: GetCoinUseCase by inject()
        val topCoinUseCase: GetTopCoinsUseCase by inject()
        val coinMarketChartUseCase: GetCoinMarketChartUseCase by inject()

        val addFavoriteCoinUseCase: AddFavoriteCoinUseCase by inject()
        val getFavoriteCoinsUseCase: GetFavoriteCoinsUseCase by inject()

        lifecycleScope.launch {
            chartData = coinMarketChartUseCase(coinId = "bitcoin", days = TimeFrame.Year.value.days)
            topCoins = topCoinUseCase(timeframe = TimeFrame.Day.value.range)
        }

        setContent {
            KoinMeterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val coins by topCoins.collectAsState(initial = emptyList())

                        LaunchedEffect(key1 = Unit) {
                            delay(1000)
                            if (coins.isNotEmpty()) {
                                addFavoriteCoinUseCase(coins.first())
                                addFavoriteCoinUseCase(coins.last())
                                firstFavCoin = coinUseCase(coins.first().id)
                            }
                        }

                        val favoriteCoins by getFavoriteCoinsUseCase().collectAsState(initial = emptyList())
                        val firstFavCoinMe by firstFavCoin.collectAsState(initial = null)

                        LazyColumn {
                            item {
                                Text(text = "My favorite coins: ${favoriteCoins.firstOrNull()?.name}")
                                Text(text = "First favorite coin: ${firstFavCoinMe?.currentPrice}")
                            }

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
