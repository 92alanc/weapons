package com.alancamargo.weapons

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val testDeviceIds = listOf(
            "2EB7091342EC4A27FC1D20FC70AF504D",
            "79CF23B940F3678EA7C7A26B1E264638"
        )
        val config = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        MobileAds.setRequestConfiguration(config)
        MobileAds.initialize(this)
    }
}
