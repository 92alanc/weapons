package com.alancamargo.weapons.webview.di

import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.analytics.AnalyticsManager
import com.alancamargo.weapons.core.di.CoreModule
import com.alancamargo.weapons.core.di.CoreToolsModule
import com.alancamargo.weapons.core.log.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        CoreToolsModule::class,
        CoreModule::class
    ]
)
internal object WebViewTestModule {

    @Provides
    @Singleton
    fun provideMockAdLoader(): AdLoader = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockLogger(): Logger = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockAnalyticsManager(): AnalyticsManager = mockk(relaxed = true)
}
