package com.ntt.androidjetpack.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BindingViewModel : ViewModel() {
    val link = MutableLiveData<String>()

    var text: String = "I love <b>Android</b> and <i>Kotlin</i>"
//    val link: LiveData<String>
//        get() = _link

    init {
        link.postValue("https://upload.wikimedia.org/wikipedia/commons/8/8b/Ngoc_Trinh_va_Fan_%28cropped%29.png")
    }
}