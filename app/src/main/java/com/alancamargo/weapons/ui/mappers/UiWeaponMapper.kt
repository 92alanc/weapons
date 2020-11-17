package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.entities.*

class UiWeaponMapper(
    private val yearMapper: EntityMapper<Year, UiYear>,
    private val manufacturerMapper: EntityMapper<Manufacturer, UiManufacturer>,
    private val countryMapper: EntityMapper<Country, UiCountry>,
    private val weaponTypeMapper: EntityMapper<WeaponType, UiWeaponType>,
    private val calibreMapper: EntityMapper<Calibre, UiCalibre>
) : EntityMapper<Weapon, UiWeapon> {

    override fun map(input: Weapon) = with(input) {
        UiWeapon(
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