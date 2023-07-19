package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.catalogue.domain.model.Calibre

interface CalibreLocalDataSource {

    suspend fun getCalibres(): List<Calibre>

    suspend fun getCalibreById(id: Long): Calibre

}