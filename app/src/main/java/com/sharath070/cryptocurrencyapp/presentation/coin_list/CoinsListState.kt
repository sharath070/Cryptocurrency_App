package com.sharath070.cryptocurrencyapp.presentation.coin_list

import com.sharath070.cryptocurrencyapp.domain.model.Coin

data class CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
