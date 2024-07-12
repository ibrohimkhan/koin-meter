package com.kodeco.koinmeter.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.koinmeter.presentation.screens.coindetails.CoinDetailScreen
import com.kodeco.koinmeter.presentation.screens.favoritecoins.FavoriteCoinScreen
import com.kodeco.koinmeter.presentation.screens.settings.SettingsScreen
import com.kodeco.koinmeter.presentation.screens.topcoins.TopCoinsScreen


const val COIN_ID_KEY = "coin_id_key"

@Composable
fun ApplicationNavigation() {
    var navigationSelectedItem by rememberSaveable { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = navigationSelectedItem == index,
                            label = { Text(navigationItem.label) },
                            icon = {
                                Icon(
                                    navigationItem.icon,
                                    contentDescription = navigationItem.label
                                )
                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
            }
        },
        modifier = Modifier.fillMaxSize(),

        ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.TopCoins.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.TopCoins.route) {
                TopCoinsScreen(
                    onItemClicked = { coinId ->
                        navController.navigate("coin_detail_route/$coinId")
                    }
                )
            }

            composable(
                route = "coin_detail_route/{$COIN_ID_KEY}",
                arguments = listOf(
                    navArgument(COIN_ID_KEY) {
                        type = NavType.StringType
                    },
                ),
            ) {
                val coinId = it.arguments!!.getString(COIN_ID_KEY)!!

                CoinDetailScreen(
                    coinId = coinId,
                    onBackClicked = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Screens.Favorite.route) {
                FavoriteCoinScreen()
            }

            composable(Screens.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
