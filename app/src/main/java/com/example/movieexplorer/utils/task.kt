package com.example.movieexplorer.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.movieexplorer.workermanager.workmanager
import java.util.concurrent.TimeUnit


fun task(context: Context){

    val batteryStatusRequest = PeriodicWorkRequestBuilder<workmanager>(
        repeatInterval = 15,
        repeatIntervalTimeUnit = TimeUnit.MINUTES
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "BatteryStatusWorker",
        ExistingPeriodicWorkPolicy.UPDATE,
        batteryStatusRequest
    )

}