package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CountryLocalDataSource
import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.entities.conversions.fromDbToDomain

class CountryLocalDataSourceImpl(private val countryDao: CountryDao) : CountryLocalDataSource {

    override suspend fun getCountries(): List<Country> = countryDao.selectAll().map {
        it.fromDbToDomain()
    }

    override suspend fun getCountryById(id: Long): Country {
        return countryDao.selectById(id).fromDbToDomain()
    }

}