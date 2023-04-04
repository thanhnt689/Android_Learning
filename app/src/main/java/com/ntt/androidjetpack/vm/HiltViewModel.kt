package com.ntt.androidjetpack.vm

import androidx.lifecycle.ViewModel
import com.ntt.androidjetpack.db.ProductDao
import com.ntt.androidjetpack.db.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(
    private val productDao: ProductDao
) : ViewModel() {
}