package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.common.ui.UiCalibre

class UiCalibreMapper : EntityMapper<Calibre, com.alancamargo.weapons.common.ui.UiCalibre> {

    override fun map(input: Calibre) = with(input) {
        com.alancamargo.weapons.common.ui.UiCalibre(id, name)
    }

}