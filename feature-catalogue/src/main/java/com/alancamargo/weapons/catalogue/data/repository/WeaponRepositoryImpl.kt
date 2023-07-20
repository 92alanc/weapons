package com.alancamargo.weapons.catalogue.data.repository

import com.alancamargo.weapons.catalogue.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.repository.WeaponRepository
import javax.inject.Inject

internal class WeaponRepositoryImpl @Inject constructor(
    private val localDataSource: WeaponLocalDataSource
) : WeaponRepository {

    override suspend fun getAllWeapons(): WeaponListResult {
        TODO("Not yet implemented")
    }

    override suspend fun filterWeaponsByName(name: String): WeaponListResult {
        TODO("Not yet implemented")
    }

    override suspend fun groupWeaponsByYear(): WeaponListResult {
        TODO("Not yet implemented")
    }

    override suspend fun groupWeaponsByCountry(): WeaponListResult {
        TODO("Not yet implemented")
    }

    override suspend fun groupWeaponsByType(): WeaponListResult {
        TODO("Not yet implemented")
    }

    override suspend fun groupWeaponsByCalibre(): WeaponListResult {
        TODO("Not yet implemented")
    }

    override suspend fun groupWeaponsByManufacturer(): WeaponListResult {
        TODO("Not yet implemented")
    }
}
