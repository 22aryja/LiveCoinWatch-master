package com.project.livecoinwatch.data.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.project.livecoinwatch.data.CryptoMapper
import com.project.livecoinwatch.data.api.ApiService
import com.project.livecoinwatch.data.database.CryptoDao
import javax.inject.Inject

class UpdateDataFactory @Inject constructor(
    private val mapper: CryptoMapper,
    private val cryptoDAO: CryptoDao,
    private val apiService: ApiService,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return UpdateData(
            appContext,
            workerParameters,
            mapper,
            cryptoDAO,
            apiService
        )
    }
}