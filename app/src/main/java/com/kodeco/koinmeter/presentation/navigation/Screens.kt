package com.kodeco.koinmeter.presentation.navigation

sealed class Screens(val route: String) {
    data object TopCoins : Screens("top_coins_route")
    data object Favorite : Screens("favorite_route")
    data object Settings : Screens("settings_route")
    data object About : Screens("about_route")
}
