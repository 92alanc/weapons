package com.alancamargo.weapons.framework.model.conversions

import com.alancamargo.weapons.domain.Calibre
import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.domain.Weapon
import com.alancamargo.weapons.domain.WeaponType
import com.alancamargo.weapons.framework.model.entities.DbCalibre
import com.alancamargo.weapons.framework.model.entities.DbCountry
import com.alancamargo.weapons.framework.model.entities.DbWeapon
import com.alancamargo.weapons.framework.model.entities.DbWeaponType
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_BOOBY_TRAP
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_CARBINE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_GRENADE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_GRENADE_LAUNCHER
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_MACHINE_GUN
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_MELEE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_MINE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_PISTOL
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_RIFLE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_ROCKET_LAUNCHER
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_SHOTGUN
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_SUB_MACHINE_GUN
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun DbWeapon.fromDbToDomain(
    dbCountry: DbCountry,
    dbType: DbWeaponType,
    dbCalibre: DbCalibre
): Weapon {
    val country = dbCountry.fromDbToDomain()
    val type = dbType.fromDbToDomain()
    val calibre = dbCalibre.fromDbToDomain()

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
        length,
        weight,
        calibre,
        capacity,
        rateOfFire,
        accuracy,
        photos
    )
}

fun Calibre.fromDomainToDb() = DbCalibre(id, calibre)

fun DbCalibre.fromDbToDomain() = Calibre(id, calibre)

fun Country.fromDomainToDb() = DbCountry(id, name, flag)

fun DbCountry.fromDbToDomain() = Country(id, name, flag)

fun WeaponType.fromDomainToDb() = when (this) {
    is WeaponType.BoobyTrap -> DbWeaponType(id, NAME_BOOBY_TRAP, category = null)
    is WeaponType.Carbine -> DbWeaponType(id, NAME_CARBINE, category = null)
    is WeaponType.Grenade -> DbWeaponType(id, NAME_GRENADE, category.name)
    is WeaponType.GrenadeLauncher -> DbWeaponType(id, NAME_GRENADE_LAUNCHER, category = null)
    is WeaponType.MachineGun -> DbWeaponType(id, NAME_MACHINE_GUN, category.name)
    is WeaponType.Melee -> DbWeaponType(id, NAME_MELEE, category = null)
    is WeaponType.Mine -> DbWeaponType(id, NAME_MINE, category.name)
    is WeaponType.Pistol -> DbWeaponType(id, NAME_PISTOL, category = null)
    is WeaponType.Rifle -> DbWeaponType(id, NAME_RIFLE, category.name)
    is WeaponType.RocketLauncher -> DbWeaponType(id, NAME_ROCKET_LAUNCHER, category = null)
    is WeaponType.Shotgun -> DbWeaponType(id, NAME_SHOTGUN, category = null)
    is WeaponType.SubMachineGun -> DbWeaponType(id, NAME_SUB_MACHINE_GUN, category = null)
}

fun DbWeaponType.fromDbToDomain() = when (this.name) {
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
    else -> throw IllegalArgumentException("Name must be a proper WeaponType name")
}

private fun DbWeaponType.convertGrenade() = when (this.category) {
    WeaponType.Grenade.Category.ANTI_PERSONNEL.name -> {
        WeaponType.Grenade(id, WeaponType.Grenade.Category.ANTI_PERSONNEL)
    }

    WeaponType.Grenade.Category.ANTI_TANK.name -> {
        WeaponType.Grenade(id, WeaponType.Grenade.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Grenade.Category")
}

private fun DbWeaponType.convertMachineGun() = when (this.category) {
    WeaponType.MachineGun.Category.GENERAL_PURPOSE.name -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.GENERAL_PURPOSE)
    }

    WeaponType.MachineGun.Category.HEAVY.name -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.HEAVY)
    }

    WeaponType.MachineGun.Category.LIGHT.name -> {
        WeaponType.MachineGun(id, WeaponType.MachineGun.Category.LIGHT)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.MachineGun.Category")
}

private fun DbWeaponType.convertMine() = when (this.category) {
    WeaponType.Mine.Category.ANTI_PERSONNEL.name -> {
        WeaponType.Mine(id, WeaponType.Mine.Category.ANTI_PERSONNEL)
    }

    WeaponType.Mine.Category.ANTI_TANK.name -> {
        WeaponType.Mine(id, WeaponType.Mine.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Mine.Category")
}

private fun DbWeaponType.convertRifle() = when (this.category) {
    WeaponType.Rifle.Category.AUTOMATIC.name -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.AUTOMATIC)
    }

    WeaponType.Rifle.Category.BOLT_ACTION.name -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.BOLT_ACTION)
    }

    WeaponType.Rifle.Category.SEMI_AUTOMATIC.name -> {
        WeaponType.Rifle(id, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Rifle.Category")
}