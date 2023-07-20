package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.model.DbCountry

class DbCountryMapper : EntityMapper<DbCountry, Country> {

    override fun map(input: DbCountry): Country = with(input) {
        Country(id, name, flagId)
    }

}