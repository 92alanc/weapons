package com.alancamargo.weapons.data.repository.type

import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.WeaponTypeLocalDataSource
import com.alancamargo.weapons.domain.entities.WeaponType

class WeaponTypeRepositoryImpl(
    private val localDataSource: WeaponTypeLocalDataSource,
    private val ioHelper: IoHelper
) : WeaponTypeRepository {

    override suspend fun getWeaponTypes(): Result<List<WeaponType>> = ioHelper.safeIoCall {
        localDataSource.getWeaponTypes()
    }

}