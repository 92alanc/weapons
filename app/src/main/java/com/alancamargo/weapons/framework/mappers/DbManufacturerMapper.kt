package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbManufacturer

class DbManufacturerMapper : EntityMapper<DbManufacturer, Manufacturer> {

    override fun map(input: DbManufacturer): Manufacturer = with(input) {
        Manufacturer(id, name)
    }

}