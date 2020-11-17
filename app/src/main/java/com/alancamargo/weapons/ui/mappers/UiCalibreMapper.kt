package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.UiCalibre

class UiCalibreMapper : EntityMapper<Calibre, UiCalibre> {

    override fun map(input: Calibre) = with(input) {
        UiCalibre(id, name)
    }

}