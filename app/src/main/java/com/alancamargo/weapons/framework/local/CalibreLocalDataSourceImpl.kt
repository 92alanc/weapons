package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CalibreLocalDataSource
import com.alancamargo.weapons.domain.Calibre
import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.model.conversions.fromDbToDomain

class CalibreLocalDataSourceImpl(private val calibreDao: CalibreDao) : CalibreLocalDataSource {

    override suspend fun getCalibres(): List<Calibre> = calibreDao.selectAll().map {
        it.fromDbToDomain()
    }

}