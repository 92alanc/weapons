package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.YearLocalDataSource
import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.db.YearDao
import com.alancamargo.weapons.framework.entities.DbYear

class YearLocalDataSourceImpl(
    private val yearDao: YearDao,
    private val mapper: EntityMapper<DbYear, Year>
) : YearLocalDataSource {

    override suspend fun getYears(): List<Year> = yearDao.selectAll().map(mapper::map)

    override suspend fun getYearById(id: Long): Year = mapper.map(yearDao.selectById(id))

}