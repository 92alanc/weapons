package com.alancamargo.weapons.framework.entities.conversions

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.framework.entities.DbWeapon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun DbWeapon.fromDbToDomain(
    manufacturer: Manufacturer?,
    country: Country?,
    type: WeaponType,
    calibre: Calibre?,
    year: Year?
): Weapon {
    val gson = Gson()
    val jsonType = object : TypeToken<List<String>>() {}.type
    val photos = gson.fromJson<List<String>>(photosJson, jsonType) ?: emptyList()

    return Weapon(
        id,
        name,
        year,
        manufacturer,
        country,
        type,
        lengthInMm,
        massInKg,
        calibre,
        capacityInRounds,
        rateOfFireInRpm,
        effectiveRangeInM,
        photos
    )
}
