package com.sharath070.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharath070.cryptocurrencyapp.common.Constants
import com.sharath070.cryptocurrencyapp.common.Resource
import com.sharath070.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.sharath070.cryptocurrencyapp.presentation.coin_list.CoinsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinsDetailState = mutableStateOf(CoinDetailState())
    val coinsDetailState: State<CoinDetailState> = _coinsDetailState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _coinsDetailState.value = CoinDetailState(isLoading = true)
                }

                is Resource.Success -> {
                    _coinsDetailState.value = CoinDetailState(coins = result.data)
                }

                is Resource.Error -> {
                    _coinsDetailState.value =
                        CoinDetailState(error = result.msg ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}