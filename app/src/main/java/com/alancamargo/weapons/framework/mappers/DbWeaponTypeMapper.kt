package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.entities.DbWeaponType
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

class DbWeaponTypeMapper : EntityMapper<DbWeaponType, WeaponType> {

    override fun map(input: DbWeaponType): WeaponType = with(input) {
        when (this.nameId) {
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

    private fun DbWeaponType.convertMine() = when (this.categoryId) {
        DbWeaponType.CATEGORY_ANTI_PERSONNEL -> {
            WeaponType.Mine(id, WeaponType.Mine.Category.ANTI_PERSONNEL)
        }

        DbWeaponType.CATEGORY_ANTI_TANK -> {
            WeaponType.Mine(id, WeaponType.Mine.Category.ANTI_TANK)
        }

        else -> throw IllegalArgumentException("Category must be a WeaponType.Mine.Category")
    }

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

}