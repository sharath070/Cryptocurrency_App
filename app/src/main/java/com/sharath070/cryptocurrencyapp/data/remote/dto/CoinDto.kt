package com.sharath070.cryptocurrencyapp.data.remote.dto

import com.sharath070.cryptocurrencyapp.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(id, name, rank, symbol, is_active)
}