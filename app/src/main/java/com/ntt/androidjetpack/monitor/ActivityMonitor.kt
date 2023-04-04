package com.ntt.androidjetpack.monitor

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.ntt.androidjetpack.util.Logger

class ActivityMonitor : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun logOnCreate() {
        Logger.logD("thanhnt", "Activity created")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun logOnStop() {
        Logger.logD("thanhnt", "Activity stopped")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun logOnStart() {
        Logger.logD("thanhnt", "Activity started")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun logOnResume() {
        Logger.logD("thanhnt", "Activity resumed")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun logOnPause() {
        Logger.logD("thanhnt", "Activity created")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun logOnDestroy() {
        Logger.logD("thanhnt", "Activity destroyed")
    }
}