package com.ntt.androidjetpack.vm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntt.androidjetpack.util.Event
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MusicViewModel(val app: Application, val tempNumber: Int) : ViewModel() {
    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int>
        get() = _number

    private val _needToast = MutableLiveData<Boolean>()
    val needToast: LiveData<Boolean>
        get() = _needToast

    private val _nextActivity = MutableLiveData<Event<Boolean>>()
    val nextActivity: LiveData<Event<Boolean>>
        get() = _nextActivity

    private val _needToastChannel = Channel<Boolean>()
    val showToastChannel = _needToastChannel.receiveAsFlow()

    init {
        _number.postValue(0)
    }

    fun showToast() {
        _needToast.postValue(true)
    }

    fun showToastChannel() {
        viewModelScope.launch {
            _needToastChannel.send(true)
        }
    }

    fun startActivity() {
        _nextActivity.postValue(Event(true))
    }

    fun increaseNumber() {
        _number.postValue(number.value?.plus(1))
    }
}