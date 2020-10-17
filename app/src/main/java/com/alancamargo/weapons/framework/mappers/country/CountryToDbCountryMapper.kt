package com.alancamargo.weapons.framework.mappers.country

import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbCountry

class CountryToDbCountryMapper : EntityMapper<Country, DbCountry> {

    override fun map(input: Country): DbCountry = with(input) {
        DbCountry(id, name, flagId)
    }

}