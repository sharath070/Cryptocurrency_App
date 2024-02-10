package com.sharath070.cryptocurrencyapp.domain.repository

import com.sharath070.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.sharath070.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}