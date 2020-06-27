package com.alancamargo.weapons.framework.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "YEAR")
data class DbYear(
    @PrimaryKey val id: Long,
    val year: Int
) {

    companion object {
        const val COLUMN_ID = "id"
    }

}