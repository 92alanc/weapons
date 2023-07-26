package com.alancamargo.weapons.catalogue.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY")
internal data class DbCountry(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "flag_id") val flagId: String
)
