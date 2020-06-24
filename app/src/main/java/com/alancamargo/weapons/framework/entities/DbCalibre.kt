package com.alancamargo.weapons.framework.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CALIBRE")
data class DbCalibre(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = COLUMN_NAME_ID) val nameId: String
) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME_ID = "name_id"
    }

}