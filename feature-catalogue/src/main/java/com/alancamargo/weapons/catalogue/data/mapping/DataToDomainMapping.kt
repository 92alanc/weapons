package com.alancamargo.weapons.catalogue.data.mapping

import com.alancamargo.weapons.catalogue.data.model.DbCalibre
import com.alancamargo.weapons.catalogue.data.model.DbCountry
import com.alancamargo.weapons.catalogue.data.model.DbMake
import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.DbYear
import com.alancamargo.weapons.catalogue.domain.model.Calibre
import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.catalogue.domain.model.Make
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.catalogue.domain.model.Year

internal fun DbWeapon.toDomain(photos: List<String>) = Weapon(
    id = weapon.id,
    name = weapon.name,
    year = year?.toDomain(),
    make = make?.toDomain(),
    country = country?.toDomain(),
    type = type.toDomain(),
    lengthInMm = weapon.lengthInMm,
    massInKg = weapon.massInKg,
    calibre = calibre?.toDomain(),
    capacityInRounds = weapon.capacityInRounds,
    rateOfFireInRpm = weapon.rateOfFireInRpm,
    effectiveRangeInM = weapon.effectiveRangeInM,
    photos = photos
)

internal fun DbYear.toDomain() = Year(
    id = id,
    year = year
)

internal fun DbMake.toDomain() = Make(
    id = id,
    name = name
)

internal fun DbCountry.toDomain() = Country(
    id = id,
    name = token.toCountryName(),
    flagId = flagId
)

internal fun DbWeaponType.toDomain() = when (nameId) {
    DbWeaponType.NAME_BOOBY_TRAP -> WeaponType.BoobyTrap(id)
    DbWeaponType.NAME_CARBINE -> WeaponType.Carbine(id)
    DbWeaponType.NAME_FLAMETHROWER -> WeaponType.Flamethrower(id)
    DbWeaponType.NAME_MELEE -> WeaponType.Melee(id)
    DbWeaponType.NAME_GRENADE_LAUNCHER -> WeaponType.GrenadeLauncher(id)
    DbWeaponType.NAME_ROCKET_LAUNCHER -> WeaponType.RocketLauncher(id)
    DbWeaponType.NAME_SHOTGUN -> WeaponType.Shotgun(id)
    DbWeaponType.NAME_SUB_MACHINE_GUN -> WeaponType.SubMachineGun(id)
    DbWeaponType.NAME_PISTOL -> WeaponType.Pistol(id)
    DbWeaponType.NAME_RIFLE -> convertRifle()
    DbWeaponType.NAME_GRENADE -> convertGrenade()
    DbWeaponType.NAME_MACHINE_GUN -> convertMachineGun()
    else -> throw IllegalArgumentException("Name must be a proper WeaponType name")
}

internal fun DbCalibre.toDomain() = Calibre(
    id = id,
    name = name
)

private fun DbWeaponType.convertRifle() = when (this.categoryId) {
    DbWeaponType.CATEGORY_AUTOMATIC -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.AUTOMATIC)
    }

    DbWeaponType.CATEGORY_BOLT_ACTION -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.BOLT_ACTION)
    }

    DbWeaponType.CATEGORY_SEMI_AUTOMATIC -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
    }

    DbWeaponType.CATEGORY_ANTI_TANK -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.ANTI_TANK)
    }

    DbWeaponType.CATEGORY_SINGLE_SHOT -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.SINGLE_SHOT)
    }

    DbWeaponType.CATEGORY_LEVER_ACTION -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.LEVER_ACTION)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Rifle.Category")
}

private fun DbWeaponType.convertGrenade() = when (this.categoryId) {
    DbWeaponType.CATEGORY_ANTI_PERSONNEL -> {
        WeaponType.Grenade(id, WeaponType.Grenade.Category.ANTI_PERSONNEL)
    }

    DbWeaponType.CATEGORY_ANTI_TANK -> {
        WeaponType.Grenade(id, WeaponType.Grenade.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Grenade.Category")
}

private fun DbWeaponType.convertMachineGun() = when (this.categoryId) {
    DbWeaponType.CATEGORY_MEDIUM -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.MEDIUM)
    }

    DbWeaponType.CATEGORY_HEAVY -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.HEAVY)
    }

    DbWeaponType.CATEGORY_LIGHT -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.LIGHT)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.MachineGun.Category")
}
