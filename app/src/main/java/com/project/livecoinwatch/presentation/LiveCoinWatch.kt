package com.project.livecoinwatch.presentation

import android.app.Application
import androidx.work.Configuration
import com.project.livecoinwatch.data.workmanager.UpdateDataFactory
import com.project.livecoinwatch.di.DaggerApplicationComponent
import javax.inject.Inject

class LiveCoinWatch : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: UpdateDataFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()


}