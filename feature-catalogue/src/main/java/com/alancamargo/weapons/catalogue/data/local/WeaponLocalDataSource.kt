package com.alancamargo.weapons.catalogue.data.local

import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader

internal interface WeaponLocalDataSource {

    suspend fun getAllWeapons(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun filterWeaponsByName(name: String): Map<WeaponListHeader?, List<Weapon>>

    suspend fun groupWeaponsByYear(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun groupWeaponsByCountry(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun groupWeaponsByType(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun groupWeaponsByCalibre(): Map<WeaponListHeader?, List<Weapon>>

    suspend fun groupWeaponsByManufacturer(): Map<WeaponListHeader?, List<Weapon>>
}
