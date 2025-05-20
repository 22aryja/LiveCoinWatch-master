package com.project.livecoinwatch.di

import android.app.Application
import com.project.livecoinwatch.presentation.CryptoDetailFragment
import com.project.livecoinwatch.presentation.CryptoPriceListActivity
import com.project.livecoinwatch.presentation.LiveCoinWatch
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(cryptoPriceListActivity: CryptoPriceListActivity)

    fun inject(cryptoDetailFragment: CryptoDetailFragment)
    fun inject(liveCoinWatch: LiveCoinWatch)

    @Component.Factory
    interface ComponentFactory{

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}