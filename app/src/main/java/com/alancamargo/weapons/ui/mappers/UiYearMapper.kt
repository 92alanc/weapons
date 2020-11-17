package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.UiYear

class UiYearMapper : EntityMapper<Year, UiYear> {

    override fun map(input: Year) = with(input) {
        UiYear(id, year)
    }

}