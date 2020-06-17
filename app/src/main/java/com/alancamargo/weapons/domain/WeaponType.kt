package com.alancamargo.weapons.domain

sealed class WeaponType {

    object Melee : WeaponType()

    object Pistol : WeaponType()

    data class Rifle(val category: Category) : WeaponType() {

        enum class Category {
            BOLT_ACTION,
            SEMI_AUTOMATIC,
            AUTOMATIC
        }

    }

    object Shotgun : WeaponType()

    object Carbine : WeaponType()

    data class MachineGun(val category: Category) : WeaponType() {

        enum class Category {
            LIGHT,
            GENERAL_PURPOSE,
            HEAVY
        }

    }

    object SubMachineGun : WeaponType()

    data class Grenade(val category: Category) : WeaponType() {

        enum class Category {
            ANTI_PERSONNEL,
            ANTI_TANK
        }

    }

    data class Mine(val category: Category) : WeaponType() {

        enum class Category {
            ANTI_PERSONNEL,
            ANTI_TANK
        }

    }

    object GrenadeLauncher : WeaponType()

    object RocketLauncher : WeaponType()

    object BoobyTrap : WeaponType()

}