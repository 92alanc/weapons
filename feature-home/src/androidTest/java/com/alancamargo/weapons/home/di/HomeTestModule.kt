package com.alancamargo.weapons.home.di

import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.analytics.AnalyticsManager
import com.alancamargo.weapons.core.consent.UserConsentManager
import com.alancamargo.weapons.core.design.di.CoreDesignModule
import com.alancamargo.weapons.core.design.dialogue.DialogueHelper
import com.alancamargo.weapons.core.di.CoreModule
import com.alancamargo.weapons.core.di.CoreToolsModule
import com.alancamargo.weapons.core.di.PreferencesModule
import com.alancamargo.weapons.core.log.Logger
import com.alancamargo.weapons.core.preferences.PreferencesManager
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import com.alancamargo.weapons.navigation.WebViewActivityNavigation
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
        CoreDesignModule::class,
        PreferencesModule::class,
        CoreModule::class
    ]
)
internal object HomeTestModule {

    @Provides
    @Singleton
    fun provideMockAdLoader(): AdLoader = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockDialogueHelper(): DialogueHelper = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockWeaponListActivityNavigation(): WeaponListActivityNavigation {
        return mockk(relaxed = true)
    }

    @Provides
    @Singleton
    fun provideMockWebViewActivityNavigation(): WebViewActivityNavigation = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockPreferencesManager(): PreferencesManager = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockLogger(): Logger = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockAnalyticsManager(): AnalyticsManager = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockUserConsentManager(): UserConsentManager = mockk(relaxed = true)
}
