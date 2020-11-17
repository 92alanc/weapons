package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.UiCountry

class UiCountryMapper : EntityMapper<Country, UiCountry> {

    override fun map(input: Country) = with(input) {
        UiCountry(id, name, flagId)
    }

}