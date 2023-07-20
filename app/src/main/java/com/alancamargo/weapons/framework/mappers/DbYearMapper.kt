package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.catalogue.domain.model.Year
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.model.DbYear

class DbYearMapper : EntityMapper<DbYear, Year> {

    override fun map(input: DbYear): Year = with(input) {
        Year(id, year)
    }

}