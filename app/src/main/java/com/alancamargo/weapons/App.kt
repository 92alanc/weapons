package com.alancamargo.weapons

import android.app.Application
import com.alancamargo.weapons.di.getModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initialiseKoin()
    }

    private fun initialiseKoin() {
        startKoin {
            androidContext(this@App)
            modules(getModules())
        }
    }

}