package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY")
internal data class DbCountry(
    @PrimaryKey val id: Long,
    val name: String,
    val flagId: String
)
