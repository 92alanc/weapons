package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.entities.Year

interface YearLocalDataSource {

    suspend fun getYears(): List<Year>

    suspend fun getYearById(id: Long): Year

}