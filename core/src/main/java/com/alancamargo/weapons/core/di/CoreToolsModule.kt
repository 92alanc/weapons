package com.alancamargo.weapons.core.di

import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.ads.AdLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class CoreToolsModule {

    @Binds
    @ActivityScoped
    abstract fun bindAdLoader(impl: AdLoaderImpl): AdLoader
}
