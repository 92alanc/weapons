package com.alancamargo.weapons.framework.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY")
data class DbCountry(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = COLUMN_NAME_ID) val nameId: String,
    @ColumnInfo(name = COLUMN_FLAG_ID) val flagId: String
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME_ID = "name_id"
        const val COLUMN_FLAG_ID = "flag_id"
    }

}