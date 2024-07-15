package com.kodeco.koinmeter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kodeco.koinmeter.presentation.navigation.ApplicationNavigation
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            KoinMeterTheme {
                ApplicationNavigation()
            }
        }
    }
}
