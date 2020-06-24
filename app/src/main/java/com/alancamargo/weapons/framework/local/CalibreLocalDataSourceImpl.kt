package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CalibreLocalDataSource
import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.entities.conversions.fromDbToDomain

class CalibreLocalDataSourceImpl(private val calibreDao: CalibreDao) : CalibreLocalDataSource {

    override suspend fun getCalibres(): List<Calibre> = calibreDao.selectAll().map {
        it.fromDbToDomain()
    }

    override suspend fun getCalibreById(id: Long): Calibre {
        return calibreDao.selectById(id).fromDbToDomain()
    }

}