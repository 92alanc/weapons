package com.alancamargo.weapons.ui.mappers

import android.content.Context
import com.alancamargo.weapons.R
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.common.ui.UiWeaponType

class UiWeaponTypeMapper(private val context: Context) : EntityMapper<WeaponType, com.alancamargo.weapons.common.ui.UiWeaponType> {

    override fun map(input: WeaponType) = with(input) {
        when (this) {
            is WeaponType.Rifle -> convertRifle(context)
            is WeaponType.MachineGun -> convertMachineGun(context)
            is WeaponType.Grenade -> convertGrenade(context)
            is WeaponType.Mine -> convertMine(context)
            is WeaponType.BoobyTrap -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_booby_trap)
            )

            is WeaponType.RocketLauncher -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_rocket_launcher)
            )

            is WeaponType.GrenadeLauncher -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_grenade_launcher)
            )

            is WeaponType.SubMachineGun -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_sub_machine_gun)
            )

            is WeaponType.Shotgun -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_shotgun)
            )
            is WeaponType.Carbine -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_carbine)
            )
            is WeaponType.Melee -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_melee)
            )
            is WeaponType.Pistol -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_pistol)
            )
            is WeaponType.Flamethrower -> com.alancamargo.weapons.common.ui.UiWeaponType(
                id,
                context.getString(R.string.type_flamethrower)
            )
        }
    }

    private fun WeaponType.Rifle.convertRifle(context: Context) = when (this.category) {
        WeaponType.Rifle.Category.AUTOMATIC -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_rifle_automatic)
        )

        WeaponType.Rifle.Category.SEMI_AUTOMATIC -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_rifle_semi_automatic)
        )

        WeaponType.Rifle.Category.BOLT_ACTION -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_rifle_bolt_action)
        )

        WeaponType.Rifle.Category.ANTI_TANK -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_rifle_anti_tank)
        )

        WeaponType.Rifle.Category.SINGLE_SHOT -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_rifle_single_shot)
        )

        WeaponType.Rifle.Category.LEVER_ACTION -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_rifle_lever_action)
        )
    }

    private fun WeaponType.MachineGun.convertMachineGun(context: Context) = when (this.category) {
        WeaponType.MachineGun.Category.MEDIUM -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_machine_gun_medium)
        )

        WeaponType.MachineGun.Category.HEAVY -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_machine_gun_heavy)
        )

        WeaponType.MachineGun.Category.LIGHT -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_machine_gun_light)
        )
    }

    private fun WeaponType.Grenade.convertGrenade(context: Context) = when (this.category) {
        WeaponType.Grenade.Category.ANTI_PERSONNEL -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_grenade_anti_personnel)
        )

        WeaponType.Grenade.Category.ANTI_TANK -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_grenade_anti_tank)
        )
    }

    private fun WeaponType.Mine.convertMine(context: Context) = when (this.category) {
        WeaponType.Mine.Category.ANTI_PERSONNEL -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_mine_anti_personnel)
        )

        WeaponType.Mine.Category.ANTI_TANK -> com.alancamargo.weapons.common.ui.UiWeaponType(
            id,
            context.getString(R.string.type_mine_anti_tank)
        )
    }

}