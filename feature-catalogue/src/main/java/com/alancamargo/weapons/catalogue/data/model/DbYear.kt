package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "YEAR")
internal data class DbYear(
    @PrimaryKey val id: Long,
    val year: Int
) {

    companion object {
        const val COLUMN_ID = "id"
    }
}
