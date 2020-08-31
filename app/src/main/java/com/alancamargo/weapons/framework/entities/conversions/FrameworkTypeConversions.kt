package com.alancamargo.weapons.framework.entities.conversions

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.framework.entities.*
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_ANTI_PERSONNEL
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_ANTI_TANK
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_AUTOMATIC
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_BOLT_ACTION
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_HEAVY
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_LIGHT
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_MEDIUM
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_SEMI_AUTOMATIC
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_BOOBY_TRAP
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_CARBINE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_FLAMETHROWER
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_GRENADE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_GRENADE_LAUNCHER
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_MACHINE_GUN
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_MELEE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_MINE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_PISTOL
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_RIFLE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_ROCKET_LAUNCHER
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_SHOTGUN
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_SUB_MACHINE_GUN
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

fun Calibre.fromDomainToDb() = DbCalibre(id, name)

fun DbCalibre.fromDbToDomain() = Calibre(id, name)

fun Country.fromDomainToDb() = DbCountry(id, name, flagId)

fun DbCountry.fromDbToDomain() = Country(id, name, flagId)

fun Manufacturer.fromDomainToDb() = DbManufacturer(id, name)

fun DbManufacturer.fromDbToDomain() = Manufacturer(id, name)

fun WeaponType.fromDomainToDb() = when (this) {
    is WeaponType.BoobyTrap -> DbWeaponType(
        id,
        NAME_BOOBY_TRAP,
        categoryId = null
    )
    is WeaponType.Carbine -> DbWeaponType(
        id,
        NAME_CARBINE,
        categoryId = null
    )
    is WeaponType.Grenade -> convertGrenade()
    is WeaponType.GrenadeLauncher -> DbWeaponType(
        id,
        NAME_GRENADE_LAUNCHER,
        categoryId = null
    )
    is WeaponType.MachineGun -> convertMachineGun()
    is WeaponType.Melee -> DbWeaponType(
        id,
        NAME_MELEE,
        categoryId = null
    )
    is WeaponType.Mine -> convertMine()
    is WeaponType.Pistol -> DbWeaponType(
        id,
        NAME_PISTOL,
        categoryId = null
    )
    is WeaponType.Rifle -> convertRifle()
    is WeaponType.RocketLauncher -> DbWeaponType(
        id,
        NAME_ROCKET_LAUNCHER,
        categoryId = null
    )
    is WeaponType.Shotgun -> DbWeaponType(
        id,
        NAME_SHOTGUN,
        categoryId = null
    )
    is WeaponType.SubMachineGun -> DbWeaponType(
        id,
        NAME_SUB_MACHINE_GUN,
        categoryId = null
    )
    is WeaponType.Flamethrower -> DbWeaponType(
        id,
        NAME_FLAMETHROWER,
        categoryId = null
    )
}

fun DbWeaponType.fromDbToDomain() = when (this.nameId) {
    NAME_BOOBY_TRAP -> WeaponType.BoobyTrap(id)
    NAME_CARBINE -> WeaponType.Carbine(id)
    NAME_GRENADE -> convertGrenade()
    NAME_GRENADE_LAUNCHER -> WeaponType.GrenadeLauncher(id)
    NAME_MACHINE_GUN -> convertMachineGun()
    NAME_MELEE -> WeaponType.Melee(id)
    NAME_MINE -> convertMine()
    NAME_PISTOL -> WeaponType.Pistol(id)
    NAME_RIFLE -> convertRifle()
    NAME_ROCKET_LAUNCHER -> WeaponType.RocketLauncher(id)
    NAME_SHOTGUN -> WeaponType.Shotgun(id)
    NAME_SUB_MACHINE_GUN -> WeaponType.SubMachineGun(id)
    NAME_FLAMETHROWER -> WeaponType.Flamethrower(id)
    else -> throw IllegalArgumentException("Name must be a proper WeaponType name")
}

fun DbYear.fromDbToDomain() = Year(id, year)

private fun WeaponType.Grenade.convertGrenade(): DbWeaponType {
    val category = when (this.category) {
        WeaponType.Grenade.Category.ANTI_PERSONNEL -> CATEGORY_ANTI_PERSONNEL
        WeaponType.Grenade.Category.ANTI_TANK -> CATEGORY_ANTI_TANK
    }

    return DbWeaponType(
        id,
        NAME_GRENADE,
        category
    )
}

private fun DbWeaponType.convertGrenade() = when (this.categoryId) {
    CATEGORY_ANTI_PERSONNEL -> {
        WeaponType.Grenade(id, WeaponType.Grenade.Category.ANTI_PERSONNEL)
    }

    CATEGORY_ANTI_TANK -> {
        WeaponType.Grenade(id, WeaponType.Grenade.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Grenade.Category")
}

private fun WeaponType.MachineGun.convertMachineGun(): DbWeaponType {
    val category = when (this.category) {
        WeaponType.MachineGun.Category.MEDIUM -> CATEGORY_MEDIUM
        WeaponType.MachineGun.Category.HEAVY -> CATEGORY_HEAVY
        WeaponType.MachineGun.Category.LIGHT -> CATEGORY_LIGHT
    }

    return DbWeaponType(
        id,
        NAME_MACHINE_GUN,
        category
    )
}

private fun DbWeaponType.convertMachineGun() = when (this.categoryId) {
    CATEGORY_MEDIUM -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.MEDIUM)
    }

    CATEGORY_HEAVY -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.HEAVY)
    }

    CATEGORY_LIGHT -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.LIGHT)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.MachineGun.Category")
}

private fun WeaponType.Mine.convertMine(): DbWeaponType {
    val category = when (this.category) {
        WeaponType.Mine.Category.ANTI_PERSONNEL -> CATEGORY_ANTI_PERSONNEL
        WeaponType.Mine.Category.ANTI_TANK -> CATEGORY_ANTI_TANK
    }

    return DbWeaponType(
        id,
        NAME_MINE,
        category
    )
}

private fun DbWeaponType.convertMine() = when (this.categoryId) {
    CATEGORY_ANTI_PERSONNEL -> {
        WeaponType.Mine(id, WeaponType.Mine.Category.ANTI_PERSONNEL)
    }

    CATEGORY_ANTI_TANK -> {
        WeaponType.Mine(id, WeaponType.Mine.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Mine.Category")
}

private fun WeaponType.Rifle.convertRifle(): DbWeaponType {
    val category = when (this.category) {
        WeaponType.Rifle.Category.BOLT_ACTION -> CATEGORY_BOLT_ACTION
        WeaponType.Rifle.Category.SEMI_AUTOMATIC -> CATEGORY_SEMI_AUTOMATIC
        WeaponType.Rifle.Category.AUTOMATIC -> CATEGORY_AUTOMATIC
        WeaponType.Rifle.Category.ANTI_TANK -> CATEGORY_ANTI_TANK
    }

    return DbWeaponType(
        id,
        NAME_RIFLE,
        category
    )
}

private fun DbWeaponType.convertRifle() = when (this.categoryId) {
    CATEGORY_AUTOMATIC -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.AUTOMATIC)
    }

    CATEGORY_BOLT_ACTION -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.BOLT_ACTION)
    }

    CATEGORY_SEMI_AUTOMATIC -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
    }

    CATEGORY_ANTI_TANK -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Rifle.Category")
}
