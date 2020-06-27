package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.YearLocalDataSource
import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.framework.db.YearDao
import com.alancamargo.weapons.framework.entities.conversions.fromDbToDomain

class YearLocalDataSourceImpl(private val yearDao: YearDao) : YearLocalDataSource {

    override suspend fun getYears(): List<Year> = yearDao.selectAll().map { it.fromDbToDomain() }

    override suspend fun getYearById(id: Long): Year = yearDao.selectById(id).fromDbToDomain()

}