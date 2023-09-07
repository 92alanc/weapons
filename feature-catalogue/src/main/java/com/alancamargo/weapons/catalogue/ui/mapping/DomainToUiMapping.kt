package com.alancamargo.weapons.catalogue.ui.mapping

import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.domain.model.Calibre
import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.catalogue.domain.model.Make
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.catalogue.domain.model.Year
import com.alancamargo.weapons.common.ui.UiCalibre
import com.alancamargo.weapons.common.ui.UiCountry
import com.alancamargo.weapons.common.ui.UiMake
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.common.ui.UiYear
import com.alancamargo.weapons.core.resources.ResourcesHelper

internal fun Weapon.toUi(resourcesHelper: ResourcesHelper) = UiWeapon(
    id = id,
    name = name,
    year = year?.toUi(),
    make = make?.toUi(),
    country = country?.toUi(),
    type = type.toUi(resourcesHelper),
    lengthInMm = lengthInMm,
    massInKg = massInKg,
    calibre = calibre?.toUi(),
    capacityInRounds = capacityInRounds,
    rateOfFireInRpm = rateOfFireInRpm,
    effectiveRangeInM = effectiveRangeInM,
    photos = photos
)

internal fun WeaponListHeader.toUi(resourcesHelper: ResourcesHelper) = when (this) {
    is Calibre -> toUi()
    is Make -> toUi()
    is Year -> toUi()
    is Country -> toUi()
    is WeaponType -> toUi(resourcesHelper)
    else -> throw IllegalStateException("Must be an implementation of WeaponListHeader")
}

private fun Year.toUi() = UiYear(
    id = id,
    year = year
)

private fun Make.toUi() = UiMake(
    id = id,
    name = name
)

private fun Country.toUi() = UiCountry(
    id = id,
    name = name.toUi(),
    flagId = flagId
)

private fun Calibre.toUi() = UiCalibre(
    id = id,
    name = name
)

private fun WeaponType.toUi(resourcesHelper: ResourcesHelper) = when (this) {
    is WeaponType.Rifle -> convertRifle(resourcesHelper)
    is WeaponType.MachineGun -> convertMachineGun(resourcesHelper)
    is WeaponType.Grenade -> convertGrenade(resourcesHelper)
    is WeaponType.Mine -> convertMine(resourcesHelper)
    is WeaponType.BoobyTrap -> UiWeaponType(id, resourcesHelper.getString(R.string.type_booby_trap))

    is WeaponType.RocketLauncher -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rocket_launcher)
    )

    is WeaponType.GrenadeLauncher -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_grenade_launcher)
    )

    is WeaponType.SubMachineGun -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_sub_machine_gun)
    )

    is WeaponType.Shotgun -> UiWeaponType(id, resourcesHelper.getString(R.string.type_shotgun))
    is WeaponType.Carbine -> UiWeaponType(id, resourcesHelper.getString(R.string.type_carbine))
    is WeaponType.Melee -> UiWeaponType(id, resourcesHelper.getString(R.string.type_melee))
    is WeaponType.Pistol -> UiWeaponType(id, resourcesHelper.getString(R.string.type_pistol))
    is WeaponType.Flamethrower -> UiWeaponType(id, resourcesHelper.getString(R.string.type_flamethrower))
}

private fun WeaponType.Rifle.convertRifle(resourcesHelper: ResourcesHelper) = when (this.category) {
    WeaponType.Rifle.Category.AUTOMATIC -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rifle_automatic)
    )

    WeaponType.Rifle.Category.SEMI_AUTOMATIC -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rifle_semi_automatic)
    )

    WeaponType.Rifle.Category.BOLT_ACTION -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rifle_bolt_action)
    )

    WeaponType.Rifle.Category.ANTI_TANK -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rifle_anti_tank)
    )

    WeaponType.Rifle.Category.SINGLE_SHOT -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rifle_single_shot)
    )

    WeaponType.Rifle.Category.LEVER_ACTION -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_rifle_lever_action)
    )
}

private fun WeaponType.MachineGun.convertMachineGun(resourcesHelper: ResourcesHelper): UiWeaponType {
    return when (this.category) {
        WeaponType.MachineGun.Category.MEDIUM -> UiWeaponType(
            id,
            resourcesHelper.getString(R.string.type_machine_gun_medium)
        )

        WeaponType.MachineGun.Category.HEAVY -> UiWeaponType(
            id,
            resourcesHelper.getString(R.string.type_machine_gun_heavy)
        )

        WeaponType.MachineGun.Category.LIGHT -> UiWeaponType(
            id,
            resourcesHelper.getString(R.string.type_machine_gun_light)
        )
    }
}

private fun WeaponType.Grenade.convertGrenade(resourcesHelper: ResourcesHelper): UiWeaponType {
    return when (this.category) {
        WeaponType.Grenade.Category.ANTI_PERSONNEL -> UiWeaponType(
            id,
            resourcesHelper.getString(R.string.type_grenade_anti_personnel)
        )

        WeaponType.Grenade.Category.ANTI_TANK -> UiWeaponType(
            id,
            resourcesHelper.getString(R.string.type_grenade_anti_tank)
        )
    }
}

private fun WeaponType.Mine.convertMine(resourcesHelper: ResourcesHelper) = when (this.category) {
    WeaponType.Mine.Category.ANTI_PERSONNEL -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_mine_anti_personnel)
    )

    WeaponType.Mine.Category.ANTI_TANK -> UiWeaponType(
        id,
        resourcesHelper.getString(R.string.type_mine_anti_tank)
    )
}
