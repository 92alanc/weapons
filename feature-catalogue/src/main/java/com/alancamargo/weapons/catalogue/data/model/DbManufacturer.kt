package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MANUFACTURER")
internal data class DbManufacturer(
    @PrimaryKey val id: Long,
    val name: String
) {

    companion object {
        const val COLUMN_ID = "id"
    }
}
