package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CALIBRE")
internal data class DbCalibre(
    @PrimaryKey val id: Long,
    val name: String
)
