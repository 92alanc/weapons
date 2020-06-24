package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.domain.entities.Country

interface CountryLocalDataSource {

    suspend fun getCountries(): List<Country>

    suspend fun getCountryById(id: Long): Country

}