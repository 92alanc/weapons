package com.alancamargo.weapons.data.repository.weapon

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.Weapon

class WeaponRepositoryImpl(
    private val localDataSource: WeaponLocalDataSource,
    private val ioHelper: IoHelper
) : WeaponRepository {

    override suspend fun getWeapons(): Result<List<Weapon>> = ioHelper.safeIoCall {
        localDataSource.getWeapons()
    }

    override suspend fun getWeaponById(id: Long): Result<Weapon> = ioHelper.safeIoCall {
        localDataSource.getWeaponById(id)
    }

    override suspend fun getWeaponsByName(name: String): Result<List<Weapon>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByName(name)
        }
    }

    override suspend fun getWeaponsByYear(year: Int): Result<List<Weapon>> = ioHelper.safeIoCall {
        localDataSource.getWeaponsByYear(year)
    }

    override suspend fun getWeaponsByCountry(countryId: Long): Result<List<Weapon>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByCountry(countryId)
        }
    }

    override suspend fun getWeaponsByType(typeId: Long): Result<List<Weapon>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByType(typeId)
        }
    }

    override suspend fun getWeaponsByCalibre(calibreId: Long): Result<List<Weapon>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByCalibre(calibreId)
        }
    }

    override suspend fun getWeaponsByManufacturer(manufacturerId: Long): Result<List<Weapon>> {
        return ioHelper.safeIoCall {
            localDataSource.getWeaponsByManufacturer(manufacturerId)
        }
    }

}