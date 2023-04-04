package com.ntt.androidjetpack.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerA3(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        for (i in 0..8) {
            Log.d("thanhnt", "Work A3 - $i")
            Thread.sleep(1000)
        }
        return Result.success()
    }
}