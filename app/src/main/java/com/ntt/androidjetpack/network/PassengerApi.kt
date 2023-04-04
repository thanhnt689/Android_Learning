package com.ntt.androidjetpack.network

import retrofit2.http.GET
import retrofit2.http.Query

interface PassengerApi {

    @GET("passenger")
    suspend fun getPassenger(
        @Query("page") page: Int,
        @Query("size") size: Int = 20
    ):PassengerResponse
}