package com.kodeco.koinmeter.presentation.navigation

sealed class Screens(val route: String) {
    object TopCoins : Screens("top_coins_route")
    object Favorite : Screens("favorite_route")
    object Settings : Screens("settings_route")
}
