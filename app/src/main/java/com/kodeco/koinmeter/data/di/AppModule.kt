package com.kodeco.koinmeter.data.di

import com.kodeco.koinmeter.data.features.topcoins.TopCoinsRepositoryImpl
import com.kodeco.koinmeter.data.features.topcoins.remote.TopCoinsRemoteDataSource
import com.kodeco.koinmeter.data.features.topcoins.remote.TopCoinsRemoteDataSourceImpl
import com.kodeco.koinmeter.domain.features.topcoins.GetCoinUseCase
import com.kodeco.koinmeter.domain.features.topcoins.GetTopCoinsUseCase
import com.kodeco.koinmeter.domain.features.topcoins.TopCoinsRepository
import org.koin.dsl.module

val topCoinModule = module {
    single<TopCoinsRemoteDataSource> { TopCoinsRemoteDataSourceImpl(get()) }
    single<TopCoinsRepository> { TopCoinsRepositoryImpl(get()) }
    factory { GetTopCoinsUseCase(get()) }
    factory { GetCoinUseCase(get()) }
}
