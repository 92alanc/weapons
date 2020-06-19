package com.alancamargo.weapons.framework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weapon")
data class DbWeapon(
    @PrimaryKey(autoGenerate = true) var id: Long = -1,
    val name: String,
    val year: Int,
    val manufacturer: String,
    val countries: String,
    val type: DbWeaponType,
    val length: Float,
    val weight: Float,
    val calibre: String,
    val capacity: Int,
    val rateOfFire: Int,
    val accuracy: Int,
    val photos: String
)