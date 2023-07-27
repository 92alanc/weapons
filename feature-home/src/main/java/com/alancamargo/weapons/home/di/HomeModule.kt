package com.alancamargo.weapons.home.di

import com.alancamargo.weapons.home.ui.analytics.HomeAnalytics
import com.alancamargo.weapons.home.ui.analytics.HomeAnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class HomeModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeAnalytics(impl: HomeAnalyticsImpl): HomeAnalytics
}
