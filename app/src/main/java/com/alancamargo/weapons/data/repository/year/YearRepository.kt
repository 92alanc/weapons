package com.alancamargo.weapons.data.repository.year

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.entities.Year

interface YearRepository {

    suspend fun getYears(): Result<List<Year>>

}