package com.alancamargo.weapons.ui.entities.conversions

import android.content.Context
import com.alancamargo.weapons.R
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.ui.entities.*

fun Weapon.fromDomainToUi(context: Context) = UiWeapon(
    id,
    name,
    year?.fromDomainToUi(),
    manufacturer?.fromDomainToUi(),
    country?.fromDomainToUi(),
    type.fromDomainToUi(context),
    length,
    weight,
    calibre?.fromDomainToUi(),
    capacity,
    rateOfFire,
    accuracy,
    photos
)

fun Country.fromDomainToUi() = UiCountry(id, name, flagId)

fun Calibre.fromDomainToUi() = UiCalibre(id, name)

fun Manufacturer.fromDomainToUi() = UiManufacturer(id, name)

fun Year.fromDomainToUi() = UiYear(id, year)

fun WeaponType.fromDomainToUi(context: Context): UiWeaponType = when (this) {
    is WeaponType.Rifle -> convertRifle(context)
    is WeaponType.MachineGun -> convertMachineGun(context)
    is WeaponType.Grenade -> convertGrenade(context)
    is WeaponType.Mine -> convertMine(context)
    is WeaponType.BoobyTrap -> UiWeaponType(id, context.getString(R.string.type_booby_trap))

    is WeaponType.RocketLauncher -> UiWeaponType(
        id,
        context.getString(R.string.type_rocket_launcher)
    )

    is WeaponType.GrenadeLauncher -> UiWeaponType(
        id,
        context.getString(R.string.type_grenade_launcher)
    )

    is WeaponType.SubMachineGun -> UiWeaponType(
        id,
        context.getString(R.string.type_sub_machine_gun)
    )

    is WeaponType.Shotgun -> UiWeaponType(id, context.getString(R.string.type_shotgun))
    is WeaponType.Carbine -> UiWeaponType(id, context.getString(R.string.type_carbine))
    is WeaponType.Melee -> UiWeaponType(id, context.getString(R.string.type_melee))
    is WeaponType.Pistol -> UiWeaponType(id, context.getString(R.string.type_pistol))
}

private fun WeaponType.Rifle.convertRifle(context: Context) = when (this.category) {
    WeaponType.Rifle.Category.AUTOMATIC -> UiWeaponType(
        id,
        context.getString(R.string.type_rifle_automatic)
    )

    WeaponType.Rifle.Category.SEMI_AUTOMATIC -> UiWeaponType(
        id,
        context.getString(R.string.type_rifle_semi_automatic)
    )

    WeaponType.Rifle.Category.BOLT_ACTION -> UiWeaponType(
        id,
        context.getString(R.string.type_rifle_bolt_action)
    )
}

private fun WeaponType.MachineGun.convertMachineGun(context: Context) = when (this.category) {
    WeaponType.MachineGun.Category.GENERAL_PURPOSE -> UiWeaponType(
        id,
        context.getString(R.string.type_machine_gun_general_purpose)
    )

    WeaponType.MachineGun.Category.HEAVY -> UiWeaponType(
        id,
        context.getString(R.string.type_machine_gun_heavy)
    )

    WeaponType.MachineGun.Category.LIGHT -> UiWeaponType(
        id,
        context.getString(R.string.type_machine_gun_light)
    )
}

private fun WeaponType.Grenade.convertGrenade(context: Context) = when (this.category) {
    WeaponType.Grenade.Category.ANTI_PERSONNEL -> UiWeaponType(
        id,
        context.getString(R.string.type_grenade_anti_personnel)
    )

    WeaponType.Grenade.Category.ANTI_TANK -> UiWeaponType(
        id,
        context.getString(R.string.type_grenade_anti_tank)
    )
}

private fun WeaponType.Mine.convertMine(context: Context) = when (this.category) {
    WeaponType.Mine.Category.ANTI_PERSONNEL -> UiWeaponType(
        id,
        context.getString(R.string.type_mine_anti_personnel)
    )

    WeaponType.Mine.Category.ANTI_TANK -> UiWeaponType(
        id,
        context.getString(R.string.type_mine_anti_tank)
    )
}
