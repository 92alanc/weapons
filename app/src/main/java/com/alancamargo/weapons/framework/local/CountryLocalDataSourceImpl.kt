package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CountryLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.db.CountryDao
import com.alancamargo.weapons.catalogue.data.model.DbCountry

class CountryLocalDataSourceImpl(
    private val countryDao: CountryDao,
    private val mapper: EntityMapper<DbCountry, Country>
) : CountryLocalDataSource {

    override suspend fun getCountries(): List<Country> = countryDao.selectAll().map(mapper::map)

    override suspend fun getCountryById(id: Long): Country = mapper.map(countryDao.selectById(id))

}