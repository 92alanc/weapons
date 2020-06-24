package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CountryLocalDataSource
import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.model.conversions.fromDbToDomain

class CountryLocalDataSourceImpl(private val countryDao: CountryDao) : CountryLocalDataSource {

    override suspend fun getCountries(): List<Country> = countryDao.selectAll().map {
        it.fromDbToDomain()
    }

}