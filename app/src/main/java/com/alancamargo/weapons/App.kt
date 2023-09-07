package com.alancamargo.weapons

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = RequestConfiguration.Builder().build()
        MobileAds.setRequestConfiguration(config)
        MobileAds.initialize(this)
    }
}