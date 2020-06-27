package com.alancamargo.weapons.framework.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CALIBRE")
data class DbCalibre(
    @PrimaryKey val id: Long,
    val name: String
) {

    companion object {
        const val COLUMN_ID = "id"
    }

}