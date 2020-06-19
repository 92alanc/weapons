package com.alancamargo.weapons.framework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class DbCountry(
    @PrimaryKey(autoGenerate = true) var id: Long = -1,
    val name: String,
    val flag: Int
)