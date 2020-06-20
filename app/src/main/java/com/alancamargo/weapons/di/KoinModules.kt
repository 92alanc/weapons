package com.alancamargo.weapons.di

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.data.repository.WeaponRepository
import com.alancamargo.weapons.data.repository.WeaponRepositoryImpl
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun getModules() = listOf(data, framework)

private val data = module {
    factory<WeaponRepository> { WeaponRepositoryImpl(get()) }
    factory<WeaponLocalDataSource> {
        // TODO
        object : WeaponLocalDataSource {
            override suspend fun getWeapons(): List<Weapon> {
                return emptyList()
            }
        }
    }
}

private val framework = module {
    factory { DatabaseProvider.getInstance(androidContext()).provideWeaponDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideWeaponTypeDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideCountryDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideCalibreDao() }
}