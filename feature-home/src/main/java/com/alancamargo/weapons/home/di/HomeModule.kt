package com.alancamargo.weapons.home.di

import com.alancamargo.weapons.home.ui.analytics.HomeAnalytics
import com.alancamargo.weapons.home.ui.analytics.HomeAnalyticsImpl
import com.alancamargo.weapons.home.ui.analytics.WeaponSearchAnalytics
import com.alancamargo.weapons.home.ui.analytics.WeaponSearchAnalyticsImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindWeaponSearchAnalytics(
        impl: WeaponSearchAnalyticsImpl
    ): WeaponSearchAnalytics
}
