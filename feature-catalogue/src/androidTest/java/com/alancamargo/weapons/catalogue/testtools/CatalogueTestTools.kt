package com.alancamargo.weapons.catalogue.testtools

import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon

internal fun stubDbWeaponList() = listOf(
    stubDbWeapon(name = "Entrenching tool"),
    stubDbWeapon(name = "Trench club"),
    stubDbWeapon(name = "Trench knife")
)

private fun stubDbWeapon(name: String) = DbWeapon(
    weapon = RawDbWeapon(
        id = 123,
        name = name,
        yearId = null,
        makeId = null,
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
    make = null,
    country = null,
    type = DbWeaponType(id = 1, nameId = "type_melee", categoryId = null),
    calibre = null
)
