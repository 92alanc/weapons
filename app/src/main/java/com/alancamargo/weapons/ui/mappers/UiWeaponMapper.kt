package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.*

class UiWeaponMapper(
    private val yearMapper: EntityMapper<Year, com.alancamargo.weapons.common.ui.UiYear>,
    private val manufacturerMapper: EntityMapper<Manufacturer, com.alancamargo.weapons.common.ui.UiManufacturer>,
    private val countryMapper: EntityMapper<Country, com.alancamargo.weapons.common.ui.UiCountry>,
    private val weaponTypeMapper: EntityMapper<WeaponType, com.alancamargo.weapons.common.ui.UiWeaponType>,
    private val calibreMapper: EntityMapper<Calibre, com.alancamargo.weapons.common.ui.UiCalibre>
) : EntityMapper<Weapon, com.alancamargo.weapons.common.ui.UiWeapon> {

    override fun map(input: Weapon) = with(input) {
        com.alancamargo.weapons.common.ui.UiWeapon(
            id,
            name,
            year?.let(yearMapper::map),
            manufacturer?.let(manufacturerMapper::map),
            country?.let(countryMapper::map),
            weaponTypeMapper.map(type),
            lengthInMm,
            massInKg,
            calibre?.let(calibreMapper::map),
            capacityInRounds,
            rateOfFireInRpm,
            effectiveRangeInM,
            photos
        )
    }

}