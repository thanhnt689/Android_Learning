package com.ntt.androidjetpack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ntt.androidjetpack.monitor.ActivityMonitor

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMonitor = ActivityMonitor()
        lifecycle.addObserver(activityMonitor)
    }
}