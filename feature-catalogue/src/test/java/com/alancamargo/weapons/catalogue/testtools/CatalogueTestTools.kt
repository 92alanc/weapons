package com.alancamargo.weapons.catalogue.testtools

import com.alancamargo.weapons.catalogue.data.mapping.toDomain
import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon

internal fun stubDbWeaponList() = listOf(
    stubDbWeapon(),
    stubDbWeapon(),
    stubDbWeapon()
)

internal fun stubWeaponList() = stubDbWeaponList().map {
    val photos = listOf(
        "photo1.jpg",
        "photo2.jpg",
        "photo3.jpg"
    )
    it.toDomain(photos)
}

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
