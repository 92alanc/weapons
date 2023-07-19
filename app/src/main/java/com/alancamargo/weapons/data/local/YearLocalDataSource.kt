package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.catalogue.domain.model.Year

interface YearLocalDataSource {

    suspend fun getYears(): List<Year>

    suspend fun getYearById(id: Long): Year

}