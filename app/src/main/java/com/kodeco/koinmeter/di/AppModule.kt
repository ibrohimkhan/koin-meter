package com.kodeco.koinmeter.di

import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSourceImpl
import com.kodeco.koinmeter.data.repository.TopCoinsRepositoryImpl
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSourceImpl
import com.kodeco.koinmeter.data.repository.CoinMarketChartRepositoryImpl
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository
import com.kodeco.koinmeter.domain.usecase.topcoins.GetCoinUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.GetCoinMarketChartUseCase
import com.kodeco.koinmeter.presentation.screens.topcoins.TopCoinsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val topCoinModule = module {
    single<TopCoinsRemoteDataSource> { TopCoinsRemoteDataSourceImpl(get()) }
    single<CoinMarketChartRemoteDataSource> { CoinMarketChartRemoteDataSourceImpl(get()) }

    single<TopCoinsRepository> { TopCoinsRepositoryImpl(get()) }
    single<CoinMarketChartRepository> { CoinMarketChartRepositoryImpl(get()) }

    factory { GetTopCoinsUseCase(get()) }
    factory { GetCoinUseCase(get()) }
    factory { GetCoinMarketChartUseCase(get()) }

    viewModel { TopCoinsViewModel(get()) }
}
