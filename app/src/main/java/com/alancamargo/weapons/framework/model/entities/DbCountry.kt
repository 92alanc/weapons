package com.alancamargo.weapons.framework.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class DbCountry(
    @PrimaryKey val id: Long,
    val name: String,
    val flag: Int
) {

    companion object {
        const val COLUMN_ID = "id"
    }

}