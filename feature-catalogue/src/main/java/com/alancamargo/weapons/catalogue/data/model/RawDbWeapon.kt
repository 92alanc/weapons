package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "WEAPON",
    foreignKeys = [
        ForeignKey(
            entity = DbManufacturer::class,
            parentColumns = ["id"],
            childColumns = ["manufacturerId"]
        ),
        ForeignKey(
            entity = DbCountry::class,
            parentColumns = ["id"],
            childColumns = ["countryId"]
        ),
        ForeignKey(
            entity = DbWeaponType::class,
            parentColumns = ["id"],
            childColumns = ["typeId"]
        ),
        ForeignKey(
            entity = DbCalibre::class,
            parentColumns = ["id"],
            childColumns = ["calibreId"]
        ),
        ForeignKey(
            entity = DbYear::class,
            parentColumns = ["id"],
            childColumns = ["yearId"]
        )
    ]
)
internal data class RawDbWeapon(
    @PrimaryKey val id: Long,
    val name: String,
    val yearId: Long?,
    val manufacturerId: Long?,
    val countryId: Long?,
    val typeId: Long,
    val lengthInMm: Int?,
    val massInKg: Float?,
    val calibreId: Long?,
    val capacityInRounds: Int?,
    val rateOfFireInRpm: Int?,
    val effectiveRangeInM: Int?
)
