package com.alancamargo.weapons.ui.entities.conversions

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.ui.entities.*

fun Weapon.fromDomainToUi() = UiWeapon(
    id,
    name,
    year.fromDomainToUi(),
    manufacturer.fromDomainToUi(),
    country.fromDomainToUi(),
    type.fromDomainToUi(),
    length,
    weight,
    calibre.fromDomainToUi(),
    capacity,
    rateOfFire,
    accuracy,
    photos
)

fun Country.fromDomainToUi() = UiCountry(id, nameId, flagId)

fun Calibre.fromDomainToUi() = UiCalibre(id, nameId)

fun Manufacturer.fromDomainToUi() = UiManufacturer(id, name)

fun Year.fromDomainToUi() = UiYear(id, year)

fun WeaponType.fromDomainToUi(): UiWeaponType = when (this) {
    is WeaponType.Rifle -> convertRifle()
    is WeaponType.MachineGun -> convertMachineGun()
    is WeaponType.Grenade -> convertGrenade()
    is WeaponType.Mine -> convertMine()
    is WeaponType.BoobyTrap -> UiWeaponType.BoobyTrap(id)
    is WeaponType.RocketLauncher -> UiWeaponType.RocketLauncher(id)
    is WeaponType.GrenadeLauncher -> UiWeaponType.GrenadeLauncher(id)
    is WeaponType.SubMachineGun -> UiWeaponType.SubMachineGun(id)
    is WeaponType.Shotgun -> UiWeaponType.Shotgun(id)
    is WeaponType.Carbine -> UiWeaponType.Carbine(id)
    is WeaponType.Melee -> UiWeaponType.Melee(id)
    is WeaponType.Pistol -> UiWeaponType.Pistol(id)
}

private fun WeaponType.Rifle.convertRifle(): UiWeaponType.Rifle {
    val category = when (this.category) {
        WeaponType.Rifle.Category.AUTOMATIC -> UiWeaponType.Rifle.Category.AUTOMATIC
        WeaponType.Rifle.Category.SEMI_AUTOMATIC -> UiWeaponType.Rifle.Category.SEMI_AUTOMATIC
        WeaponType.Rifle.Category.BOLT_ACTION -> UiWeaponType.Rifle.Category.BOLT_ACTION
    }

    return UiWeaponType.Rifle(id, category)
}

private fun WeaponType.MachineGun.convertMachineGun(): UiWeaponType.MachineGun {
    val category = when (this.category) {
        WeaponType.MachineGun.Category.GENERAL_PURPOSE -> {
            UiWeaponType.MachineGun.Category.GENERAL_PURPOSE
        }

        WeaponType.MachineGun.Category.HEAVY -> UiWeaponType.MachineGun.Category.HEAVY
        WeaponType.MachineGun.Category.LIGHT -> UiWeaponType.MachineGun.Category.LIGHT
    }

    return UiWeaponType.MachineGun(id, category)
}

private fun WeaponType.Grenade.convertGrenade(): UiWeaponType.Grenade {
    val category = when (this.category) {
        WeaponType.Grenade.Category.ANTI_PERSONNEL -> UiWeaponType.Grenade.Category.ANTI_PERSONNEL
        WeaponType.Grenade.Category.ANTI_TANK -> UiWeaponType.Grenade.Category.ANTI_TANK
    }

    return UiWeaponType.Grenade(id, category)
}

private fun WeaponType.Mine.convertMine(): UiWeaponType.Mine {
    val category = when (this.category) {
        WeaponType.Mine.Category.ANTI_PERSONNEL -> UiWeaponType.Mine.Category.ANTI_PERSONNEL
        WeaponType.Mine.Category.ANTI_TANK -> UiWeaponType.Mine.Category.ANTI_TANK
    }

    return UiWeaponType.Mine(id, category)
}