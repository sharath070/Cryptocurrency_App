package com.sharath070.cryptocurrencyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharath070.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.sharath070.cryptocurrencyapp.presentation.coin_list.CoinListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.CoinListScreen.name) {
        composable(Screens.CoinListScreen.name) {
            CoinListScreen(navController = navController)
        }
        val route = Screens.CoinDetailScreen.name
        composable(route = "$route/{coinId}") {
            CoinDetailScreen()
        }
    }
}