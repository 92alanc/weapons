package com.alancamargo.weapons.catalogue.data.repository

import com.alancamargo.weapons.catalogue.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.repository.WeaponRepository
import com.alancamargo.weapons.core.log.Logger
import javax.inject.Inject

internal class WeaponRepositoryImpl @Inject constructor(
    private val localDataSource: WeaponLocalDataSource,
    private val logger: Logger
) : WeaponRepository {

    override suspend fun getAllWeapons(): WeaponListResult {
        return safelyGetWeaponListResult(localDataSource::getAllWeapons)
    }

    override suspend fun filterWeaponsByName(name: String): WeaponListResult {
        return safelyGetWeaponListResult {
            localDataSource.filterWeaponsByName(name)
        }
    }

    override suspend fun groupWeaponsByYear(): WeaponListResult {
        return safelyGetWeaponListResult(localDataSource::groupWeaponsByYear)
    }

    override suspend fun groupWeaponsByCountry(): WeaponListResult {
        return safelyGetWeaponListResult(localDataSource::groupWeaponsByCountry)
    }

    override suspend fun groupWeaponsByType(): WeaponListResult {
        return safelyGetWeaponListResult(localDataSource::groupWeaponsByType)
    }

    override suspend fun groupWeaponsByCalibre(): WeaponListResult {
        return safelyGetWeaponListResult(localDataSource::groupWeaponsByCalibre)
    }

    override suspend fun groupWeaponsByManufacturer(): WeaponListResult {
        return safelyGetWeaponListResult(localDataSource::groupWeaponsByManufacturer)
    }

    private suspend fun safelyGetWeaponListResult(
        operation: suspend () -> Map<WeaponListHeader?, List<Weapon>>
    ): WeaponListResult {
        return try {
            WeaponListResult.Success(operation())
        } catch (t: Throwable) {
            logger.error(t)
            WeaponListResult.Error
        }
    }
}
