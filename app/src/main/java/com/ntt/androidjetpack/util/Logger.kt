package com.ntt.androidjetpack.util

import android.util.Log
import com.ntt.androidjetpack.BuildConfig

object Logger {
    fun logD(tag: String, message: String) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag,Log.DEBUG)) {
            Log.d(tag, message)
        }
    }

    fun logE(tag: String, message: String) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag,Log.ERROR)) {
            Log.d(tag, message)
        }
    }
}