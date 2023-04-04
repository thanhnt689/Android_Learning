package com.ntt.androidjetpack.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PassengerClient {
    private val BASE_URL = "https://api.instantwebtools.net/v1/"

    operator fun invoke(): PassengerApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PassengerApi::class.java)
}