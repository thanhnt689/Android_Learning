package com.ntt.androidjetpack.network

import com.ntt.androidjetpack.model.Passenger

data class PassengerResponse(
    val totalPassengers: Int,
    val totalPages:Int,
    val data:List<Passenger>
)
