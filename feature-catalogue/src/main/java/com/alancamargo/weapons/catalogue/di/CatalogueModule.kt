package com.alancamargo.weapons.catalogue.di

import com.alancamargo.weapons.catalogue.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.catalogue.data.local.WeaponLocalDataSourceImpl
import com.alancamargo.weapons.catalogue.data.repository.WeaponRepositoryImpl
import com.alancamargo.weapons.catalogue.domain.repository.WeaponRepository
import com.alancamargo.weapons.catalogue.domain.usecase.GetWeaponsUseCase
import com.alancamargo.weapons.catalogue.domain.usecase.GetWeaponsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class CatalogueModule {

    @Binds
    @ViewModelScoped
    abstract fun bindWeaponLocalDataSource(impl: WeaponLocalDataSourceImpl): WeaponLocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindWeaponRepository(impl: WeaponRepositoryImpl): WeaponRepository

    @Binds
    @ViewModelScoped
    abstract fun bindGetWeaponsUseCase(impl: GetWeaponsUseCaseImpl): GetWeaponsUseCase
}
