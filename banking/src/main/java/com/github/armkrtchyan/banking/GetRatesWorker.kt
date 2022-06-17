package com.github.armkrtchyan.banking

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.github.armkrtchyan.domain.repositories.RatesRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest

class GetRatesWorker(private val mRatesRepository: RatesRepository, context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("WorkManager", "Work start")
        var res = Result.success()
        mRatesRepository.getRates().catch {
            res = Result.Failure()
        }.collectLatest {
            res = Result.success(workDataOf("result" to Gson().toJson(it)))
        }
        Log.d("WorkManager", "Work finished")
        return res
    }
}