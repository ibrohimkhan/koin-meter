package com.kodeco.koinmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.kodeco.koinmeter.model.TimeFrame
import com.kodeco.koinmeter.networking.RemoteApiService
import com.kodeco.koinmeter.networking.dto.CoinDto
import com.kodeco.koinmeter.ui.theme.KoinMeterTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var coins by mutableStateOf(emptyList<CoinDto>())

        val apiService: RemoteApiService by inject()

        lifecycleScope.launch {
            val response = apiService.getTopCoins(percentageTimeframe = TimeFrame.Day.value)

            if (response.isSuccessful) {
                coins = response.body() ?: emptyList()
            }
        }

        setContent {
            KoinMeterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        LazyColumn {
                            items(coins) { coin ->
                                Text(text = coin.name ?: "N/A")
                            }
                        }
                    }
                }
            }
        }
    }
}
