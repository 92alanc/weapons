package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.ManufacturerLocalDataSource
import com.alancamargo.weapons.domain.Manufacturer
import com.alancamargo.weapons.framework.db.ManufacturerDao
import com.alancamargo.weapons.framework.model.conversions.fromDbToDomain

class ManufacturerLocalDataSourceImpl(
    private val manufacturerDao: ManufacturerDao
) : ManufacturerLocalDataSource {

    override suspend fun getManufacturers(): List<Manufacturer> = manufacturerDao.selectAll().map {
        it.fromDbToDomain()
    }

}