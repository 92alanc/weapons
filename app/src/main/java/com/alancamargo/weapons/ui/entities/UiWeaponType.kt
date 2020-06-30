package com.alancamargo.weapons.ui.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO: implement SimpleTextEntity
sealed class UiWeaponType(open val id: Long) : Parcelable {

    @Parcelize
    class Melee(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class Pistol(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class Rifle(override val id: Long, val category: Category) : UiWeaponType(id) {

        enum class Category {
            BOLT_ACTION,
            SEMI_AUTOMATIC,
            AUTOMATIC
        }

    }

    @Parcelize
    class Shotgun(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class Carbine(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class MachineGun(override val id: Long, val category: Category) : UiWeaponType(id) {

        enum class Category {
            LIGHT,
            GENERAL_PURPOSE,
            HEAVY
        }

    }

    @Parcelize
    class SubMachineGun(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class Grenade(override val id: Long, val category: Category) : UiWeaponType(id) {

        enum class Category {
            ANTI_PERSONNEL,
            ANTI_TANK
        }

    }

    @Parcelize
    class Mine(override val id: Long, val category: Category) : UiWeaponType(id) {

        enum class Category {
            ANTI_PERSONNEL,
            ANTI_TANK
        }

    }

    @Parcelize
    class GrenadeLauncher(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class RocketLauncher(override val id: Long) : UiWeaponType(id)

    @Parcelize
    class BoobyTrap(override val id: Long) : UiWeaponType(id)

}