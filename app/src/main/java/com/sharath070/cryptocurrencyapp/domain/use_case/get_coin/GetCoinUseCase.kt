package com.sharath070.cryptocurrencyapp.domain.use_case.get_coin

import com.sharath070.cryptocurrencyapp.common.Resource
import com.sharath070.cryptocurrencyapp.data.remote.dto.toCoin
import com.sharath070.cryptocurrencyapp.data.remote.dto.toCoinDetails
import com.sharath070.cryptocurrencyapp.domain.model.Coin
import com.sharath070.cryptocurrencyapp.domain.model.CoinDetails
import com.sharath070.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetails()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}