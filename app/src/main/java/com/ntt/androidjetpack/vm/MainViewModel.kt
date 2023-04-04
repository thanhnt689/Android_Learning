package com.ntt.androidjetpack.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int>
        get() = _number

    init {
        _number.postValue(0)
    }

    fun increaseNumber() {
        _number.postValue(_number.value?.plus(1))
    }
}