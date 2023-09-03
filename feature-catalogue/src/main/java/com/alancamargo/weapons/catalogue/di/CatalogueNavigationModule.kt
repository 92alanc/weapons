package com.alancamargo.weapons.catalogue.di

import com.alancamargo.weapons.catalogue.ui.navigation.WeaponListActivityNavigationImpl
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class CatalogueNavigationModule {

    @Binds
    @ActivityScoped
    abstract fun bindWeaponListActivityNavigation(
        impl: WeaponListActivityNavigationImpl
    ): WeaponListActivityNavigation
}
