package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbCountry

class DbCountryMapper : EntityMapper<DbCountry, Country> {

    override fun map(input: DbCountry): Country = with(input) {
        Country(id, name, flagId)
    }

}