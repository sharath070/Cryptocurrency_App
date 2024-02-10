package com.sharath070.cryptocurrencyapp.domain.model

import com.sharath070.cryptocurrencyapp.data.remote.dto.TeamMembers

data class CoinDetails (
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val is_active: Boolean,
    val tags: List<String>,
    val team: List<TeamMembers>
)