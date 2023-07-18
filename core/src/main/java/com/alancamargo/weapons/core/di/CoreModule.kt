package com.alancamargo.weapons.core.di

import com.alancamargo.weapons.core.analytics.AnalyticsManager
import com.alancamargo.weapons.core.analytics.AnalyticsManagerImpl
import com.alancamargo.weapons.core.log.Logger
import com.alancamargo.weapons.core.log.LoggerImpl
import com.google.android.datatransport.runtime.dagger.Provides
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreModule {

    @Provides
    @Singleton
    fun provideLogger(
        firebaseCrashlytics: FirebaseCrashlytics
    ): Logger = LoggerImpl(firebaseCrashlytics)

    @Provides
    @Singleton
    fun provideAnalyticsManager(
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsManager = AnalyticsManagerImpl(firebaseAnalytics)
}
