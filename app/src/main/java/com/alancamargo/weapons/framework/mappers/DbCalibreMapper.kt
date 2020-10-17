package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbCalibre

class DbCalibreMapper : EntityMapper<DbCalibre, Calibre> {

    override fun map(input: DbCalibre): Calibre = with(input) {
        Calibre(id, name)
    }

}