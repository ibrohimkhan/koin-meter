package com.kodeco.koinmeter.di

import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSource
import com.kodeco.koinmeter.data.local.datasource.coinmarketchart.CoinMarketChartLocalDataSourceImpl
import com.kodeco.koinmeter.data.local.datasource.favoritecoins.FavoriteCoinsLocalDataSource
import com.kodeco.koinmeter.data.local.datasource.favoritecoins.FavoriteCoinsLocalDataSourceImpl
import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSource
import com.kodeco.koinmeter.data.local.datasource.topcoins.TopCoinsLocalDataSourceImpl
import com.kodeco.koinmeter.data.local.provideCoinDao
import com.kodeco.koinmeter.data.local.provideCoinMarketChartDao
import com.kodeco.koinmeter.data.local.provideDatabase
import com.kodeco.koinmeter.data.local.provideFavoriteCoinDao
import com.kodeco.koinmeter.data.prefs.TimeFramePrefs
import com.kodeco.koinmeter.data.prefs.TimeFramePrefsImpl
import com.kodeco.koinmeter.data.remote.adapters.CoinAdapter
import com.kodeco.koinmeter.data.remote.adapters.CoinMarketChartPriceAdapter
import com.kodeco.koinmeter.data.remote.adapters.LocalDateTimeAdapter
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSource
import com.kodeco.koinmeter.data.remote.datasource.coinmarketchart.CoinMarketChartRemoteDataSourceImpl
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.data.remote.datasource.topcoins.TopCoinsRemoteDataSourceImpl
import com.kodeco.koinmeter.data.remote.provideApiService
import com.kodeco.koinmeter.data.remote.provideHttpClient
import com.kodeco.koinmeter.data.remote.provideMoshi
import com.kodeco.koinmeter.data.remote.provideRetrofit
import com.kodeco.koinmeter.data.repository.CoinMarketChartRepositoryImpl
import com.kodeco.koinmeter.data.repository.FavoriteCoinsRepositoryImpl
import com.kodeco.koinmeter.data.repository.TopCoinsRepositoryImpl
import com.kodeco.koinmeter.domain.repository.CoinMarketChartRepository
import com.kodeco.koinmeter.domain.repository.FavoriteCoinsRepository
import com.kodeco.koinmeter.domain.repository.TopCoinsRepository
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.DeleteAllCoinMarketChartsUseCase
import com.kodeco.koinmeter.domain.usecase.coinmarketchart.GetCoinMarketChartUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.AddFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.ContainsFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.DeleteFavoriteCoinUseCase
import com.kodeco.koinmeter.domain.usecase.favoritecoins.GetFavoriteCoinsUseCase
import com.kodeco.koinmeter.domain.usecase.settings.GetTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.settings.UpdateTimeFrameSettingsUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetCoinUseCase
import com.kodeco.koinmeter.domain.usecase.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.presentation.screens.coindetails.CoinDetailViewModel
import com.kodeco.koinmeter.presentation.screens.topcoins.TopCoinsViewModel
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkingModule = module {
    single { CoinAdapter() }
    single { CoinMarketChartPriceAdapter() }
    single { LocalDateTimeAdapter() }
    single { KotlinJsonAdapterFactory() }
    single { provideMoshi(get(), get(), get(), get()) }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideApiService(get()) }
}

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideCoinDao(get()) }
    single { provideCoinMarketChartDao(get()) }
    single { provideFavoriteCoinDao(get()) }
}

val dataStorePrefsModule = module {
    single<TimeFramePrefs> { TimeFramePrefsImpl(get()) }
}

val topCoinModule = module {
    single<TopCoinsRemoteDataSource> { TopCoinsRemoteDataSourceImpl(get()) }
    single<TopCoinsLocalDataSource> { TopCoinsLocalDataSourceImpl(get()) }

    single<TopCoinsRepository> { TopCoinsRepositoryImpl(get(), get()) }

    factory { GetTopCoinsUseCase(get()) }
    factory { GetCoinUseCase(get()) }

    viewModel { TopCoinsViewModel(get(), get()) }
}

val coinMarketChartModule = module {
    single<CoinMarketChartRemoteDataSource> { CoinMarketChartRemoteDataSourceImpl(get()) }
    single<CoinMarketChartLocalDataSource> { CoinMarketChartLocalDataSourceImpl(get()) }

    single<CoinMarketChartRepository> { CoinMarketChartRepositoryImpl(get(), get()) }

    factory { GetCoinMarketChartUseCase(get()) }
    factory { DeleteAllCoinMarketChartsUseCase(get()) }

    viewModel { CoinDetailViewModel(get(), get(), get(), get(), get(), get()) }
}

val favoriteCoinsModule = module {
    single<FavoriteCoinsLocalDataSource> { FavoriteCoinsLocalDataSourceImpl(get()) }
    single<FavoriteCoinsRepository> { FavoriteCoinsRepositoryImpl(get()) }

    factory { GetFavoriteCoinsUseCase(get()) }
    factory { AddFavoriteCoinUseCase(get()) }
    factory { DeleteFavoriteCoinUseCase(get()) }
    factory { ContainsFavoriteCoinUseCase(get()) }
}

val timeFrameSettingsModule = module {
    factory { GetTimeFrameSettingsUseCase(get()) }
    factory { UpdateTimeFrameSettingsUseCase(get(), get()) }
}
