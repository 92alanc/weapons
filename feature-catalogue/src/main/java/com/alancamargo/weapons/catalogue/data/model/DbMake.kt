package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MAKE")
internal data class DbMake(
    @PrimaryKey val id: Long,
    val name: String
)
