package com.alancamargo.weapons.data.repository.weapon

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.domain.entities.WeaponListHeader

interface WeaponRepository {

    suspend fun getWeapons(): Result<Map<WeaponListHeader?, List<Weapon>>>

    suspend fun getWeaponsByName(name: String): Result<Map<WeaponListHeader?, List<Weapon>>>

    suspend fun getWeaponsByYear(): Result<Map<WeaponListHeader?, List<Weapon>>>

    suspend fun getWeaponsByCountry(): Result<Map<WeaponListHeader?, List<Weapon>>>

    suspend fun getWeaponsByType(): Result<Map<WeaponListHeader?, List<Weapon>>>

    suspend fun getWeaponsByCalibre(): Result<Map<WeaponListHeader?, List<Weapon>>>

    suspend fun getWeaponsByManufacturer(): Result<Map<WeaponListHeader?, List<Weapon>>>

}