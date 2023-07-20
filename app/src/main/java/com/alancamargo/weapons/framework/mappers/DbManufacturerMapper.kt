package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.catalogue.domain.model.Manufacturer
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.model.DbManufacturer

class DbManufacturerMapper : EntityMapper<DbManufacturer, Manufacturer> {

    override fun map(input: DbManufacturer): Manufacturer = with(input) {
        Manufacturer(id, name)
    }

}