package com.irfan.capcoba

import android.app.Application
import com.irfan.core.di.databaseModule
import com.irfan.core.di.networkModule
import com.irfan.core.di.repositoryModule
import com.irfan.capcoba.ui.di.useCaseModule
import com.irfan.capcoba.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }


}