package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.WeaponTypeLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.db.WeaponTypeDao
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType

class WeaponTypeLocalDataSourceImpl(
    private val weaponTypeDao: WeaponTypeDao,
    private val mapper: EntityMapper<DbWeaponType, WeaponType>
) : WeaponTypeLocalDataSource {

    override suspend fun getWeaponTypes(): List<WeaponType> = weaponTypeDao.selectAll().map {
        mapper.map(it)
    }

    override suspend fun getWeaponTypeById(id: Long): WeaponType {
        return mapper.map(weaponTypeDao.selectById(id))
    }

}