package com.alancamargo.weapons

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = RequestConfiguration.Builder()
            .setTestDeviceIds(listOf("2EB7091342EC4A27FC1D20FC70AF504D"))
            .build()
        MobileAds.setRequestConfiguration(config)
        MobileAds.initialize(this)
    }
}