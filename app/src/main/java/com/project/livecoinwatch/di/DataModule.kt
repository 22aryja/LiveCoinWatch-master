package com.project.livecoinwatch.di

import android.app.Application
import com.project.livecoinwatch.data.api.ApiFactory
import com.project.livecoinwatch.data.api.ApiService
import com.project.livecoinwatch.data.database.AppDatabase
import com.project.livecoinwatch.data.database.CryptoDao
import com.project.livecoinwatch.data.repository.CryptoRepositoryImpl
import com.project.livecoinwatch.domain.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImpl): CryptoRepository

    companion object{

        @Provides
        @ApplicationScope
        fun provideCryptoInfoDAO(application: Application):CryptoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}