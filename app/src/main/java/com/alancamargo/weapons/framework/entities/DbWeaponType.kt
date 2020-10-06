package com.alancamargo.weapons.framework.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WEAPON_TYPE")
data class DbWeaponType(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = COLUMN_NAME_ID) val nameId: String,
    @ColumnInfo(name = COLUMN_CATEGORY_ID) val categoryId: String?
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME_ID = "name_id"
        const val COLUMN_CATEGORY_ID = "category_id"

        const val NAME_MELEE = "type_melee"
        const val NAME_PISTOL = "type_pistol"
        const val NAME_RIFLE = "type_rifle"
        const val NAME_SHOTGUN = "type_shotgun"
        const val NAME_CARBINE = "type_carbine"
        const val NAME_MACHINE_GUN = "type_machine_gun"
        const val NAME_SUB_MACHINE_GUN = "type_sub_machine_gun"
        const val NAME_GRENADE = "type_grenade"
        const val NAME_MINE = "type_mine"
        const val NAME_GRENADE_LAUNCHER = "type_grenade_launcher"
        const val NAME_ROCKET_LAUNCHER = "type_rocket_launcher"
        const val NAME_BOOBY_TRAP = "type_booby_trap"
        const val NAME_FLAMETHROWER = "type_flamethrower"

        const val CATEGORY_BOLT_ACTION = "category_bolt_action"
        const val CATEGORY_SEMI_AUTOMATIC = "category_semi_automatic"
        const val CATEGORY_AUTOMATIC = "category_automatic"
        const val CATEGORY_LIGHT = "category_light"
        const val CATEGORY_MEDIUM = "category_medium"
        const val CATEGORY_HEAVY = "category_heavy"
        const val CATEGORY_ANTI_PERSONNEL = "category_anti_personnel"
        const val CATEGORY_ANTI_TANK = "category_anti_tank"
        const val CATEGORY_SINGLE_SHOT = "category_single_shot"
        const val CATEGORY_LEVER_ACTION = "category_lever_action"
    }

}