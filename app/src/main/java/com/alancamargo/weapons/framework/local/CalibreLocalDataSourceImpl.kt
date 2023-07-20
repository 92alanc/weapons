package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CalibreLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.Calibre
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.db.CalibreDao
import com.alancamargo.weapons.catalogue.data.model.DbCalibre

class CalibreLocalDataSourceImpl(
    private val calibreDao: CalibreDao,
    private val mapper: EntityMapper<DbCalibre, Calibre>
) : CalibreLocalDataSource {

    override suspend fun getCalibres(): List<Calibre> = calibreDao.selectAll().map(mapper::map)

    override suspend fun getCalibreById(id: Long): Calibre = mapper.map(calibreDao.selectById(id))

}