package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.common.ui.UiManufacturer

class UiManufacturerMapper : EntityMapper<Manufacturer, com.alancamargo.weapons.common.ui.UiManufacturer> {

    override fun map(input: Manufacturer) = with(input) {
        com.alancamargo.weapons.common.ui.UiManufacturer(id, name)
    }

}