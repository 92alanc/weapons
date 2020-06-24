package com.alancamargo.weapons.data.repository.calibre

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.entities.Calibre

interface CalibreRepository {

    suspend fun getCalibres(): Result<List<Calibre>>

}