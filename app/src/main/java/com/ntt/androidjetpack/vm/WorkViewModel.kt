package com.ntt.androidjetpack.vm

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.ntt.androidjetpack.worker.*
import java.util.concurrent.TimeUnit

class WorkViewModel(private val app: Application) : AndroidViewModel(app) {

    private val workManager = WorkManager.getInstance(app.applicationContext)
    val sampleWorkInfo = workManager.getWorkInfosByTagLiveData("download_link")

    fun downloadContent() {
        val workConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
//            .setRequiresStorageNotLow(true)
            .build()
//        val downloadRequest = OneTimeWorkRequest.from(SampleWorker::class.java)
        val downloadRequest = OneTimeWorkRequestBuilder<SampleWorker>()
        downloadRequest.setConstraints(workConstraints)

        workManager.enqueue(downloadRequest.build())
    }

    fun downloadContentLoop() {
        val downloadRequest = PeriodicWorkRequestBuilder<SampleWorker>(20, TimeUnit.SECONDS)
        workManager.enqueueUniquePeriodicWork(
            "download",
            ExistingPeriodicWorkPolicy.REPLACE,
            downloadRequest.build()
        )
    }

    fun downloadLink(link: String) {
        val downloadRequest = OneTimeWorkRequestBuilder<SampleWorker>()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
        downloadRequest.setConstraints(constraints)

        val workData = workDataOf(
            "sample" to "ThanhNt",
            "number" to 100,
            "link" to link
        )

        val data = Data.Builder()
        data.putString("link", link)
        data.putAll(workData)
        downloadRequest.setInputData(data.build())
        downloadRequest.addTag("download_link")
        val request = downloadRequest.build()
        workManager.enqueueUniqueWork(
            "download_with_link",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    fun runChain() {
        val workA1 = OneTimeWorkRequest.from(WorkerA1::class.java)
        val workA2 = OneTimeWorkRequest.from(WorkerA2::class.java)
        val workA3 = OneTimeWorkRequest.from(WorkerA3::class.java)
        val workB = OneTimeWorkRequest.from(WorkerB::class.java)
        val workC = OneTimeWorkRequest.from(WorkerC::class.java)
        workManager
            .beginWith(listOf(workA1, workA2, workA3))
            .then(workB)
            .then(workC).enqueue()
    }

    @SuppressLint("EnqueueWork")
    fun runWorkContinuation() {
        val workA1 = OneTimeWorkRequest.from(WorkerA1::class.java)
        val workA2 = OneTimeWorkRequest.from(WorkerA2::class.java)
        val workA3 = OneTimeWorkRequest.from(WorkerA3::class.java)
        val workB = OneTimeWorkRequest.from(WorkerB::class.java)

        val workC = OneTimeWorkRequest.from(WorkerC::class.java)

        val chain1 = workManager
            .beginWith(workA1)
            .then(workA2) //15s
        val chain2 = workManager
            .beginWith(workA3)
            .then(workB)//8s

        val chain3 = WorkContinuation
            .combine(listOf(chain1, chain2))
            .then(workC)
            .enqueue()
    }

}