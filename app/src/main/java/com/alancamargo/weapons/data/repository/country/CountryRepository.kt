package com.alancamargo.weapons.data.repository.country

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.domain.Country

interface CountryRepository {

    suspend fun getCountries(): Result<List<Country>>

}