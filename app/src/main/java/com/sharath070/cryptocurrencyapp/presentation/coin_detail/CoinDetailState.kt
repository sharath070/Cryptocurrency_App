package com.sharath070.cryptocurrencyapp.presentation.coin_detail

import com.sharath070.cryptocurrencyapp.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetails? = null,
    val error: String = ""
)
