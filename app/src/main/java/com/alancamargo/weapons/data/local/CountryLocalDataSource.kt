package com.alancamargo.weapons.data.local

import com.alancamargo.weapons.catalogue.domain.model.Country

interface CountryLocalDataSource {

    suspend fun getCountries(): List<Country>

    suspend fun getCountryById(id: Long): Country

}