package com.alancamargo.weapons.framework.model.conversions

import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.domain.WeaponType
import com.alancamargo.weapons.framework.model.DbCountry
import com.alancamargo.weapons.framework.model.DbWeaponType
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_BOOBY_TRAP
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_CARBINE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_GRENADE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_GRENADE_LAUNCHER
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_MACHINE_GUN
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_MELEE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_MINE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_PISTOL
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_RIFLE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_ROCKET_LAUNCHER
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_SHOTGUN
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_SUB_MACHINE_GUN

fun Country.fromDomainToDb() = DbCountry(name = name, flag = flag)

fun DbCountry.fromDbToDomain() = Country(name, flag)

fun WeaponType.fromDomainToDb() = when (this) {
    is WeaponType.BoobyTrap -> DbWeaponType(name = NAME_BOOBY_TRAP, category = null)
    is WeaponType.Carbine -> DbWeaponType(name = NAME_CARBINE, category = null)
    is WeaponType.Grenade -> DbWeaponType(name = NAME_GRENADE, category = this.category.name)
    is WeaponType.GrenadeLauncher -> DbWeaponType(name = NAME_GRENADE_LAUNCHER, category = null)
    is WeaponType.MachineGun -> DbWeaponType(name = NAME_MACHINE_GUN, category = this.category.name)
    is WeaponType.Melee -> DbWeaponType(name = NAME_MELEE, category = null)
    is WeaponType.Mine -> DbWeaponType(name = NAME_MINE, category = this.category.name)
    is WeaponType.Pistol -> DbWeaponType(name = NAME_PISTOL, category = null)
    is WeaponType.Rifle -> DbWeaponType(name = NAME_RIFLE, category = this.category.name)
    is WeaponType.RocketLauncher -> DbWeaponType(name = NAME_ROCKET_LAUNCHER, category = null)
    is WeaponType.Shotgun -> DbWeaponType(name = NAME_SHOTGUN, category = null)
    is WeaponType.SubMachineGun -> DbWeaponType(name = NAME_SUB_MACHINE_GUN, category = null)
}

fun DbWeaponType.fromDbToDomain() = when (this.name) {
    NAME_BOOBY_TRAP -> WeaponType.BoobyTrap
    NAME_CARBINE -> WeaponType.Carbine
    NAME_GRENADE -> convertGrenade()
    NAME_GRENADE_LAUNCHER -> WeaponType.GrenadeLauncher
    NAME_MACHINE_GUN -> convertMachineGun()
    NAME_MELEE -> WeaponType.Melee
    NAME_MINE -> convertMine()
    NAME_PISTOL -> WeaponType.Pistol
    NAME_RIFLE -> convertRifle()
    NAME_ROCKET_LAUNCHER -> WeaponType.RocketLauncher
    NAME_SHOTGUN -> WeaponType.Shotgun
    NAME_SUB_MACHINE_GUN -> WeaponType.SubMachineGun
    else -> throw IllegalArgumentException("Name must be a proper WeaponType name")
}

private fun DbWeaponType.convertGrenade() = when (this.category) {
    WeaponType.Grenade.Category.ANTI_PERSONNEL.name -> {
        WeaponType.Grenade(WeaponType.Grenade.Category.ANTI_PERSONNEL)
    }

    WeaponType.Grenade.Category.ANTI_TANK.name -> {
        WeaponType.Grenade(WeaponType.Grenade.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Grenade.Category")
}

private fun DbWeaponType.convertMachineGun() = when (this.category) {
    WeaponType.MachineGun.Category.GENERAL_PURPOSE.name -> {
        WeaponType.MachineGun(WeaponType.MachineGun.Category.GENERAL_PURPOSE)
    }

    WeaponType.MachineGun.Category.HEAVY.name -> {
        WeaponType.MachineGun(WeaponType.MachineGun.Category.HEAVY)
    }

    WeaponType.MachineGun.Category.LIGHT.name -> {
        WeaponType.MachineGun(WeaponType.MachineGun.Category.LIGHT)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.MachineGun.Category")
}

private fun DbWeaponType.convertMine() = when (this.category) {
    WeaponType.Mine.Category.ANTI_PERSONNEL.name -> {
        WeaponType.Mine(WeaponType.Mine.Category.ANTI_PERSONNEL)
    }

    WeaponType.Mine.Category.ANTI_TANK.name -> {
        WeaponType.Mine(WeaponType.Mine.Category.ANTI_TANK)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Mine.Category")
}

private fun DbWeaponType.convertRifle() = when (this.category) {
    WeaponType.Rifle.Category.AUTOMATIC.name -> {
        WeaponType.Rifle(WeaponType.Rifle.Category.AUTOMATIC)
    }

    WeaponType.Rifle.Category.BOLT_ACTION.name -> {
        WeaponType.Rifle(WeaponType.Rifle.Category.BOLT_ACTION)
    }

    WeaponType.Rifle.Category.SEMI_AUTOMATIC.name -> {
        WeaponType.Rifle(WeaponType.Rifle.Category.SEMI_AUTOMATIC)
    }

    else -> throw IllegalArgumentException("Category must be a WeaponType.Rifle.Category")
}