package com.alancamargo.weapons.catalogue.domain.repository

import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult

internal interface WeaponRepository {

    suspend fun getAllWeapons(): WeaponListResult

    suspend fun filterWeaponsByName(name: String): WeaponListResult

    suspend fun groupWeaponsByYear(): WeaponListResult

    suspend fun groupWeaponsByCountry(): WeaponListResult

    suspend fun groupWeaponsByType(): WeaponListResult

    suspend fun groupWeaponsByCalibre(): WeaponListResult

    suspend fun groupWeaponsByMake(): WeaponListResult
}
