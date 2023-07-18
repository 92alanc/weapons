package com.alancamargo.weapons.core.design.di

import com.alancamargo.weapons.core.design.ads.AdLoader
import com.alancamargo.weapons.core.design.ads.AdLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class CoreDesignModule {

    @Binds
    @ActivityScoped
    abstract fun bindAdLoader(impl: AdLoaderImpl): AdLoader
}
