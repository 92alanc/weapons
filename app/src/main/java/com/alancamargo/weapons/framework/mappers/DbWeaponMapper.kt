package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbWeapon

class DbWeaponMapper(
    private val params: Params
) : EntityMapper<DbWeapon, Weapon> {

    override fun map(input: DbWeapon): Weapon = with(input) {
        Weapon(
            id,
            name,
            params.year,
            params.manufacturer,
            params.country,
            params.type,
            lengthInMm,
            massInKg,
            params.calibre,
            capacityInRounds,
            rateOfFireInRpm,
            effectiveRangeInM,
            params.photos
        )
    }

    data class Params(
        val year: Year?,
        val manufacturer: Manufacturer?,
        val country: Country?,
        val type: WeaponType,
        val calibre: Calibre?,
        val photos: List<String>
    )

}