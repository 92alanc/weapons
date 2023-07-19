package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.common.ui.UiCountry

class UiCountryMapper : EntityMapper<Country, com.alancamargo.weapons.common.ui.UiCountry> {

    override fun map(input: Country) = with(input) {
        com.alancamargo.weapons.common.ui.UiCountry(id, name, flagId)
    }

}