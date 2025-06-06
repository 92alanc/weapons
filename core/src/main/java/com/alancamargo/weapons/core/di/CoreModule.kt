package com.alancamargo.weapons.core.di

import android.content.Context
import com.alancamargo.weapons.core.analytics.AnalyticsManager
import com.alancamargo.weapons.core.analytics.AnalyticsManagerImpl
import com.alancamargo.weapons.core.database.DatabaseProvider
import com.alancamargo.weapons.core.database.DatabaseProviderImpl
import com.alancamargo.weapons.core.log.Logger
import com.alancamargo.weapons.core.log.LoggerImpl
import com.alancamargo.weapons.core.remoteconfig.RemoteConfigManager
import com.alancamargo.weapons.core.remoteconfig.RemoteConfigManagerImpl
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.core.resources.ResourcesHelperImpl
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideLogger(
        firebaseCrashlytics: FirebaseCrashlytics
    ): Logger = LoggerImpl(firebaseCrashlytics)

    @Provides
    @Singleton
    fun provideAnalyticsManager(
        firebaseAnalytics: FirebaseAnalytics,
        logger: Logger
    ): AnalyticsManager = AnalyticsManagerImpl(firebaseAnalytics, logger)

    @Provides
    @Singleton
    fun provideDatabaseProvider(
        @ApplicationContext context: Context
    ): DatabaseProvider = DatabaseProviderImpl(context)

    @Provides
    @Singleton
    fun provideResourcesHelper(
        @ApplicationContext context: Context,
        logger: Logger
    ): ResourcesHelper = ResourcesHelperImpl(context, logger)

    @Provides
    @Singleton
    fun provideRemoteConfigManager(
        firebaseRemoteConfig: FirebaseRemoteConfig,
        logger: Logger
    ): RemoteConfigManager = RemoteConfigManagerImpl(
        firebaseRemoteConfig,
        logger
    )
}
