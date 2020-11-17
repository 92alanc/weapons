package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.UiManufacturer

class UiManufacturerMapper : EntityMapper<Manufacturer, UiManufacturer> {

    override fun map(input: Manufacturer) = with(input) {
        UiManufacturer(id, name)
    }

}