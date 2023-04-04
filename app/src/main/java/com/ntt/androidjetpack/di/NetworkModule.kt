package com.ntt.androidjetpack.di

import com.ntt.androidjetpack.network.PassengerApi
import com.ntt.androidjetpack.network.PassengerClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BASE_URL = "https://api.instantwebtools.net/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providerPassengerApi(retrofit: Retrofit): PassengerApi {
        return retrofit.create(PassengerApi::class.java)
    }
}