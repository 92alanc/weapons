package com.alancamargo.weapons.domain.entities

sealed class WeaponType(val id: Long) : WeaponListHeader {

    class Melee(id: Long) : WeaponType(id)

    class Pistol(id: Long) : WeaponType(id)

    class Rifle(id: Long, val category: Category) : WeaponType(id) {

        enum class Category {
            BOLT_ACTION,
            SEMI_AUTOMATIC,
            AUTOMATIC,
            ANTI_TANK,
            SINGLE_SHOT
        }

    }

    class Shotgun(id: Long) : WeaponType(id)

    class Carbine(id: Long) : WeaponType(id)

    class MachineGun(id: Long, val category: Category) : WeaponType(id) {

        enum class Category {
            LIGHT,
            MEDIUM,
            HEAVY
        }

    }

    class SubMachineGun(id: Long) : WeaponType(id)

    class Grenade(id: Long, val category: Category) : WeaponType(id) {

        enum class Category {
            ANTI_PERSONNEL,
            ANTI_TANK
        }

    }

    class Mine(id: Long, val category: Category) : WeaponType(id) {

        enum class Category {
            ANTI_PERSONNEL,
            ANTI_TANK
        }

    }

    class GrenadeLauncher(id: Long) : WeaponType(id)

    class RocketLauncher(id: Long) : WeaponType(id)

    class BoobyTrap(id: Long) : WeaponType(id)

    class Flamethrower(id: Long) : WeaponType(id)

}