package com.alancamargo.weapons.framework.mappers.calibre

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbCalibre

class CalibreToDbCalibreMapper : EntityMapper<Calibre, DbCalibre> {

    override fun map(input: Calibre): DbCalibre = with(input) {
        DbCalibre(id, name)
    }

}