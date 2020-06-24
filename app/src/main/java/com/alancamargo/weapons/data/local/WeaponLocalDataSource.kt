package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.Weapon

interface WeaponLocalDataSource {

    suspend fun getWeapons(): List<Weapon>

    suspend fun getWeaponsByName(name: String): List<Weapon>

    suspend fun getWeaponsByYear(year: Int): List<Weapon>

    suspend fun getWeaponsByCountry(countryId: Long): List<Weapon>

    suspend fun getWeaponsByType(typeId: Long): List<Weapon>

    suspend fun getWeaponsByCalibre(calibreId: Long): List<Weapon>

    suspend fun getWeaponsByManufacturer(manufacturerId: Long): List<Weapon>

}