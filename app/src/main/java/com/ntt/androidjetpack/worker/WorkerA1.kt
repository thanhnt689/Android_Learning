package com.ntt.androidjetpack.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerA1(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        for (i in 0..10) {
            Log.d("thanhnt", "Work A1 - $i")
            Thread.sleep(1000)
        }
        return Result.success()
    }
}