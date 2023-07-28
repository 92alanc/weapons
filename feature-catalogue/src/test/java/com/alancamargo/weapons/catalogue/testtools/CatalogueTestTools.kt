package com.alancamargo.weapons.catalogue.testtools

import com.alancamargo.weapons.catalogue.data.mapping.toDomain
import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponType

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

internal fun stubWeaponListWithHeaderMap(): Map<WeaponListHeader?, List<Weapon>> = mapOf(
    WeaponType.Melee(id = 123) to stubWeaponList(),
    WeaponType.Melee(id = 123) to stubWeaponList()
)

internal fun stubWeaponListMap(): Map<WeaponListHeader?, List<Weapon>> = mapOf(
    null to stubWeaponList()
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
