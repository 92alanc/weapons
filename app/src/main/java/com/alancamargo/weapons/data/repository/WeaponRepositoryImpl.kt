package com.alancamargo.weapons.data.repository

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon

class WeaponRepositoryImpl(private val localDataSource: WeaponLocalDataSource) : WeaponRepository {

    override suspend fun getWeapons(): List<Weapon> = localDataSource.getWeapons()

}