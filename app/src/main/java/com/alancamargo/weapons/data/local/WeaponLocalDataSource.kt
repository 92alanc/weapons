package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader

interface WeaponLocalDataSource {

    suspend fun getWeapons(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun getWeaponsByName(name: String): Map<WeaponListHeader?, List<Weapon>>

    suspend fun getWeaponsByYear(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun getWeaponsByCountry(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun getWeaponsByType(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun getWeaponsByCalibre(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun getWeaponsByManufacturer(): Map<WeaponListHeader?, List<Weapon>>

}