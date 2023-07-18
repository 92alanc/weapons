package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.common.ui.UiYear

class UiYearMapper : EntityMapper<Year, com.alancamargo.weapons.common.ui.UiYear> {

    override fun map(input: Year) = with(input) {
        com.alancamargo.weapons.common.ui.UiYear(id, year)
    }

}