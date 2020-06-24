package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.Calibre

interface CalibreLocalDataSource {

    suspend fun getCalibres(): List<Calibre>

}