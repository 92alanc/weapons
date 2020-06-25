package com.alancamargo.weapons

import androidx.multidex.MultiDexApplication
import com.alancamargo.weapons.di.getModules
import com.google.android.gms.ads.MobileAds
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initialiseKoin()
        MobileAds.initialize(this)
    }

    private fun initialiseKoin() {
        startKoin {
            androidContext(this@App)
            modules(getModules())
        }
    }

}