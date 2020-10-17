package com.alancamargo.weapons.framework.mappers.calibre

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbCalibre

class DbCalibreToCalibreMapper : EntityMapper<DbCalibre, Calibre> {

    override fun map(input: DbCalibre): Calibre = with(input) {
        Calibre(id, name)
    }
}