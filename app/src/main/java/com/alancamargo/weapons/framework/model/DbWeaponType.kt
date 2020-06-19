package com.alancamargo.weapons.framework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeaponType")
data class DbWeaponType(
    @PrimaryKey(autoGenerate = true) var id: Long = -1,
    val name: String,
    val category: String?
) {

    companion object {
        const val NAME_MELEE = "Melee"
        const val NAME_PISTOL = "Pistol"
        const val NAME_RIFLE = "Rifle"
        const val NAME_SHOTGUN = "Shotgun"
        const val NAME_CARBINE = "Carbine"
        const val NAME_MACHINE_GUN = "MachineGun"
        const val NAME_SUB_MACHINE_GUN = "SubMachineGun"
        const val NAME_GRENADE = "Grenade"
        const val NAME_MINE = "Mine"
        const val NAME_GRENADE_LAUNCHER = "GrenadeLauncher"
        const val NAME_ROCKET_LAUNCHER = "RocketLauncher"
        const val NAME_BOOBY_TRAP = "BoobyTrap"
    }

}