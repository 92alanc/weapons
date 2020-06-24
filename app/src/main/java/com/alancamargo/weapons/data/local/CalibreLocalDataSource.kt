package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.entities.Calibre

interface CalibreLocalDataSource {

    suspend fun getCalibres(): List<Calibre>

    suspend fun getCalibreById(id: Long): Calibre

}