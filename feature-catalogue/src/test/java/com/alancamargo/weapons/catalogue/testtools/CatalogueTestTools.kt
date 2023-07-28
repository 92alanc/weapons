package com.alancamargo.weapons.catalogue.testtools

import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon

internal fun stubDbWeaponList() = listOf(
    stubDbWeapon(),
    stubDbWeapon(),
    stubDbWeapon()
)

private fun stubDbWeapon() = DbWeapon(
    weapon = RawDbWeapon(
        id = 123,
        name = "Weapon",
        yearId = null,
        manufacturerId = null,
        countryId = null,
        typeId = 1,
        lengthInMm = null,
        massInKg = null,
        calibreId = null,
        capacityInRounds = null,
        rateOfFireInRpm = null,
        effectiveRangeInM = null
    ),
    year = null,
    manufacturer = null,
    country = null,
    type = DbWeaponType(id = 1, nameId = "type_melee", categoryId = null),
    calibre = null
)
