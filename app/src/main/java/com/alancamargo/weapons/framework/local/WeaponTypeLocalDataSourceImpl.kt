package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.WeaponTypeLocalDataSource
import com.alancamargo.weapons.domain.entities.WeaponType
import com.alancamargo.weapons.framework.db.WeaponTypeDao
import com.alancamargo.weapons.framework.entities.conversions.fromDbToDomain

class WeaponTypeLocalDataSourceImpl(
    private val weaponTypeDao: WeaponTypeDao
) : WeaponTypeLocalDataSource {

    override suspend fun getWeaponTypes(): List<WeaponType> = weaponTypeDao.selectAll().map {
        it.fromDbToDomain()
    }

    override suspend fun getWeaponTypeById(id: Long): WeaponType {
        return weaponTypeDao.selectById(id).fromDbToDomain()
    }

}