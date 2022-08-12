package com.alancamargo.weapons

import android.app.Application
import com.alancamargo.weapons.di.KoinAppDeclarationProvider
import com.smaato.sdk.core.Config
import com.smaato.sdk.core.SmaatoSdk
import com.smaato.sdk.core.log.LogLevel
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = KoinAppDeclarationProvider.provideAppDeclaration(this))
        initialiseSmaato()
    }

    private fun initialiseSmaato() {
        val config = Config.builder().setLogLevel(LogLevel.ERROR).build()
        val publisherId = BuildConfig.SMAATO_PUBLISHER_ID
        SmaatoSdk.init(this, config, publisherId)
    }

}