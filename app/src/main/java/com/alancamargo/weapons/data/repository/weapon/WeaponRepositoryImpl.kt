package com.alancamargo.weapons.data.repository.weapon

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.domain.entities.WeaponListHeader

class WeaponRepositoryImpl(
    private val localDataSource: WeaponLocalDataSource,
    private val ioHelper: IoHelper
) : WeaponRepository {

    override suspend fun getWeapons(): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeapons()
        }
    }

    override suspend fun getWeaponsByName(
        name: String
    ): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByName(name)
        }
    }

    override suspend fun getWeaponsByYear(): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByYear()
        }
    }

    override suspend fun getWeaponsByCountry(): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByCountry()
        }
    }

    override suspend fun getWeaponsByType(): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByType()
        }
    }

    override suspend fun getWeaponsByCalibre(): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByCalibre()
        }
    }

    override suspend fun getWeaponsByManufacturer(): Result<Map<WeaponListHeader?, List<Weapon>>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByManufacturer()
        }
    }

}