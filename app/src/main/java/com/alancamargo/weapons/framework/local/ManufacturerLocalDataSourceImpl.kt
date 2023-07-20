package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.ManufacturerLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.Manufacturer
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.db.ManufacturerDao
import com.alancamargo.weapons.catalogue.data.model.DbManufacturer

class ManufacturerLocalDataSourceImpl(
    private val manufacturerDao: ManufacturerDao,
    private val mapper: EntityMapper<DbManufacturer, Manufacturer>
) : ManufacturerLocalDataSource {

    override suspend fun getManufacturers(): List<Manufacturer> = manufacturerDao.selectAll().map {
        mapper.map(it)
    }

    override suspend fun getManufacturerById(id: Long): Manufacturer {
        return mapper.map(manufacturerDao.selectById(id))
    }

}