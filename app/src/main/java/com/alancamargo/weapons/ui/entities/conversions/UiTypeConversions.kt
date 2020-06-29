package com.alancamargo.weapons.ui.entities.conversions

import com.alancamargo.weapons.R
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

fun UiWeaponType.fromUiToStringId(): Int = when (this) {
    is UiWeaponType.BoobyTrap -> R.string.type_booby_trap
    is UiWeaponType.Carbine -> R.string.type_carbine
    is UiWeaponType.GrenadeLauncher -> R.string.type_grenade_launcher
    is UiWeaponType.Melee -> R.string.type_melee
    is UiWeaponType.Pistol -> R.string.type_pistol
    is UiWeaponType.RocketLauncher -> R.string.type_rocket_launcher
    is UiWeaponType.Shotgun -> R.string.type_shotgun
    is UiWeaponType.SubMachineGun -> R.string.type_sub_machine_gun
    is UiWeaponType.Rifle -> convertRifle()
    is UiWeaponType.Grenade -> convertGrenade()
    is UiWeaponType.Mine -> convertMine()
    is UiWeaponType.MachineGun -> convertMachineGun()
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

private fun UiWeaponType.Rifle.convertRifle() = when (category) {
    UiWeaponType.Rifle.Category.AUTOMATIC -> R.string.type_rifle_automatic
    UiWeaponType.Rifle.Category.SEMI_AUTOMATIC -> R.string.type_rifle_semi_automatic
    UiWeaponType.Rifle.Category.BOLT_ACTION -> R.string.type_rifle_bolt_action
}

private fun UiWeaponType.Grenade.convertGrenade() = when (category) {
    UiWeaponType.Grenade.Category.ANTI_PERSONNEL -> R.string.type_grenade_anti_personnel
    UiWeaponType.Grenade.Category.ANTI_TANK -> R.string.type_grenade_anti_tank
}

private fun UiWeaponType.Mine.convertMine() = when (category) {
    UiWeaponType.Mine.Category.ANTI_PERSONNEL -> R.string.type_mine_anti_personnel
    UiWeaponType.Mine.Category.ANTI_TANK -> R.string.type_mine_anti_tank
}

private fun UiWeaponType.MachineGun.convertMachineGun() = when (category) {
    UiWeaponType.MachineGun.Category.GENERAL_PURPOSE -> R.string.type_machine_gun_general_purpose
    UiWeaponType.MachineGun.Category.HEAVY -> R.string.type_machine_gun_heavy
    UiWeaponType.MachineGun.Category.LIGHT -> R.string.type_machine_gun_light
}