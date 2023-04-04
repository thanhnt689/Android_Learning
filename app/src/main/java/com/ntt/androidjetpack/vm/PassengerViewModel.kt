package com.ntt.androidjetpack.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ntt.androidjetpack.network.PassengerApi
import com.ntt.androidjetpack.network.PassengerClient
import com.ntt.androidjetpack.network.PassengerDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PassengerViewModel @Inject constructor(
    private val passengerApi: PassengerApi
) : ViewModel() {

    val passengers = Pager(PagingConfig(pageSize = 20)) {
        PassengerDataSource(passengerApi)
    }.flow.cachedIn(viewModelScope)
}