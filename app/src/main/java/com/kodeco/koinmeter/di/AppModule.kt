package com.kodeco.koinmeter.di

import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSource
import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSourceImpl
import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSource
import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSourceImpl
import com.kodeco.koinmeter.data.local.provideCoinDao
import com.kodeco.koinmeter.data.local.provideCoinMarketChartDao
import com.kodeco.koinmeter.data.local.provideDatabase
import com.kodeco.koinmeter.data.remote.adapters.CoinAdapter
import com.kodeco.koinmeter.data.remote.adapters.CoinMarketChartPriceAdapter
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSourceImpl
import com.kodeco.koinmeter.data.repository.TopCoinsRepositoryImpl
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSourceImpl
import com.kodeco.koinmeter.data.remote.provideApiService
import com.kodeco.koinmeter.data.remote.provideHttpClient
import com.kodeco.koinmeter.data.remote.provideMoshi
import com.kodeco.koinmeter.data.remote.provideRetrofit
import com.kodeco.koinmeter.data.repository.CoinMarketChartRepositoryImpl
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository
import com.kodeco.koinmeter.domain.usecase.topcoins.GetCoinUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.GetCoinMarketChartUseCase
import com.kodeco.koinmeter.presentation.screens.topcoins.TopCoinsViewModel
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkingModule = module {
    single { CoinAdapter() }
    single { CoinMarketChartPriceAdapter() }
    single { KotlinJsonAdapterFactory() }
    single { provideMoshi(get(), get(), get()) }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideApiService(get()) }
}

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideCoinDao(get()) }
    single { provideCoinMarketChartDao(get()) }
}

val topCoinModule = module {
    single<TopCoinsRemoteDataSource> { TopCoinsRemoteDataSourceImpl(get()) }
    single<TopCoinsLocalDataSource> { TopCoinsLocalDataSourceImpl(get()) }

    single<TopCoinsRepository> { TopCoinsRepositoryImpl(get(), get()) }

    factory { GetTopCoinsUseCase(get()) }
    factory { GetCoinUseCase(get()) }

    viewModel { TopCoinsViewModel(get()) }
}

val coinMarketChartModule = module {
    single<CoinMarketChartRemoteDataSource> { CoinMarketChartRemoteDataSourceImpl(get()) }
    single<CoinMarketChartLocalDataSource> { CoinMarketChartLocalDataSourceImpl(get()) }

    single<CoinMarketChartRepository> { CoinMarketChartRepositoryImpl(get(), get()) }

    factory { GetCoinMarketChartUseCase(get()) }
}
