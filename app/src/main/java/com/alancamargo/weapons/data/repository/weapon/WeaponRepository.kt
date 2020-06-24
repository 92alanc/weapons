package com.alancamargo.weapons.data.repository.weapon

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.entities.Weapon

interface WeaponRepository {

    suspend fun getWeapons(): Result<List<Weapon>>

    suspend fun getWeaponsByName(name: String): Result<List<Weapon>>

    suspend fun getWeaponsByYear(year: Int): Result<List<Weapon>>

    suspend fun getWeaponsByCountry(countryId: Long): Result<List<Weapon>>

    suspend fun getWeaponsByType(typeId: Long): Result<List<Weapon>>

    suspend fun getWeaponsByCalibre(calibreId: Long): Result<List<Weapon>>

    suspend fun getWeaponsByManufacturer(manufacturerId: Long): Result<List<Weapon>>

}