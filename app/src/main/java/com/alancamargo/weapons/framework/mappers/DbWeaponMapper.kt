package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbWeapon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DbWeaponMapper(
    private val year: Year?,
    private val manufacturer: Manufacturer?,
    private val country: Country?,
    private val type: WeaponType,
    private val calibre: Calibre?
) : EntityMapper<DbWeapon, Weapon> {

    override fun map(input: DbWeapon): Weapon = with(input) {
        val gson = Gson()
        val jsonType = object : TypeToken<List<String>>() {}.type
        val photos = gson.fromJson<List<String>>(photosJson, jsonType) ?: emptyList()

        Weapon(
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

}