package com.kodeco.koinmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.kodeco.koinmeter.presentation.navigation.ApplicationNavigation
import com.kodeco.koinmeter.presentation.screens.splash.SplashScreen
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinMeterTheme {
                var isSplashScreenVisible by rememberSaveable { mutableStateOf(true) }

                LaunchedEffect(null) {
                    delay(3000)
                    isSplashScreenVisible = false
                }

                when (isSplashScreenVisible) {
                    true -> SplashScreen()
                    false -> ApplicationNavigation()
                }
            }
        }
    }
}
