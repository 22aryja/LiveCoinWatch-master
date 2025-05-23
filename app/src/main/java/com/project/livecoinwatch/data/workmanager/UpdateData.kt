package com.project.livecoinwatch.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.project.livecoinwatch.data.CryptoMapper
import com.project.livecoinwatch.data.api.ApiService
import com.project.livecoinwatch.data.database.CryptoDao
import kotlinx.coroutines.delay

class UpdateData(
    context: Context,
    workerParameters: WorkerParameters,
    private val mapper: CryptoMapper,
    private val cryptoDAO: CryptoDao,
    private val apiService: ApiService,
) : CoroutineWorker(context, workerParameters){


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCryptoInfoDTO(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDTOtoDatabase(it) }
                cryptoDAO.insertPriceList(dbModelList)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            delay(1000000)
        }
    }

    companion object {

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<UpdateData>().build()
        }
    }
}