package com.ntt.androidjetpack.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class SampleWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val link = inputData.getString("link")
        return try {
            downloadFileFromNetwork(link!!)
            val outData = workDataOf("isSuccess" to true, "image" to "path")
            Result.success(outData)
        } catch (e: Exception) {
            val outData = workDataOf("isSuccess" to false)
            Result.failure(outData)
        }
    }

    private fun downloadFileFromNetwork(link: String) {
        Log.d("thanhnt", "Download content from $link")
    }
}