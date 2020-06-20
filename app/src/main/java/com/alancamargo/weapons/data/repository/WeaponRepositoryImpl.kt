package com.alancamargo.weapons.data.repository

import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon

class WeaponRepositoryImpl(private val localDataSource: WeaponLocalDataSource) : WeaponRepository {

    override suspend fun getWeapons(): List<Weapon> = localDataSource.getWeapons()

    override suspend fun getWeaponById(id: Long): Weapon = localDataSource.getWeaponById(id)

    override suspend fun getWeaponsByName(name: String): List<Weapon> {
        return localDataSource.getWeaponsByName(name)
    }

    override suspend fun getWeaponsByYear(year: Int): List<Weapon> {
        return localDataSource.getWeaponsByYear(year)
    }

    override suspend fun getWeaponsByCountry(countryId: Long): List<Weapon> {
        return localDataSource.getWeaponsByCountry(countryId)
    }

    override suspend fun getWeaponsByType(typeId: Long): List<Weapon> {
        return localDataSource.getWeaponsByType(typeId)
    }

    override suspend fun getWeaponsByCalibre(calibreId: Long): List<Weapon> {
        return localDataSource.getWeaponsByCalibre(calibreId)
    }

}