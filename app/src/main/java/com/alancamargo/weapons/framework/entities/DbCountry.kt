package com.alancamargo.weapons.framework.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY")
data class DbCountry(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = COLUMN_FLAG_ID) val flagId: String
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_FLAG_ID = "flag_id"
    }

}