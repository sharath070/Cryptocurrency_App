package com.sharath070.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharath070.cryptocurrencyapp.common.Resource
import com.sharath070.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _coinsState = mutableStateOf(CoinsListState())
    val coinsState: State<CoinsListState> = _coinsState

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _coinsState.value = CoinsListState(isLoading = true)
                }

                is Resource.Success -> {
                    _coinsState.value = CoinsListState(coins = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _coinsState.value =
                        CoinsListState(error = result.msg ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}