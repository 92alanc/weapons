package com.alancamargo.weapons.di

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.data.repository.WeaponRepository
import com.alancamargo.weapons.data.repository.WeaponRepositoryImpl
import com.alancamargo.weapons.domain.Weapon
import org.koin.dsl.module

fun getModules() = listOf(data, framework)

private val data = module {
    factory<WeaponRepository> { WeaponRepositoryImpl(get()) }
}

private val framework = module {
    factory<WeaponLocalDataSource> {
        // TODO
        object : WeaponLocalDataSource {
            override suspend fun getWeapons(): List<Weapon> {
                return emptyList()
            }
        }
    }
}