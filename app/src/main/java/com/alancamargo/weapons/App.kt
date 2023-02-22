package com.alancamargo.weapons

import android.app.Application
import com.alancamargo.weapons.di.KoinAppDeclarationProvider
import com.google.android.gms.ads.MobileAds
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = KoinAppDeclarationProvider.provideAppDeclaration(this))
        MobileAds.initialize(this)
    }
}