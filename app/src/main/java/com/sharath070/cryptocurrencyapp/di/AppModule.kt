package com.sharath070.cryptocurrencyapp.di

import com.sharath070.cryptocurrencyapp.common.Constants
import com.sharath070.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.sharath070.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.sharath070.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun providesCoinPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}