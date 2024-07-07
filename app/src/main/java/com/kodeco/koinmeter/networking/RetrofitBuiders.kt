package com.kodeco.koinmeter.networking

import com.kodeco.koinmeter.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun provideHttpClient(): OkHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("x-cg-demo-api-key", BuildConfig.API_KEY)
            .build()
        chain.proceed(request)
    }
    .build()

fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

fun provideApiService(retrofit: Retrofit): RemoteApiService =
    retrofit.create(RemoteApiService::class.java)

val networkingModule = module {
    single { provideMoshi() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideApiService(get()) }
}
