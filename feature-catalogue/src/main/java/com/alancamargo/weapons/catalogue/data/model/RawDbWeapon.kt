package com.alancamargo.weapons.catalogue.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "WEAPON",
    foreignKeys = [
        ForeignKey(
            entity = DbManufacturer::class,
            parentColumns = ["id"],
            childColumns = ["manufacturer_id"]
        ),
        ForeignKey(
            entity = DbCountry::class,
            parentColumns = ["id"],
            childColumns = ["country_id"]
        ),
        ForeignKey(
            entity = DbWeaponType::class,
            parentColumns = ["id"],
            childColumns = ["type_id"]
        ),
        ForeignKey(
            entity = DbCalibre::class,
            parentColumns = ["id"],
            childColumns = ["calibre_id"]
        ),
        ForeignKey(
            entity = DbYear::class,
            parentColumns = ["id"],
            childColumns = ["year_id"]
        )
    ]
)
internal data class RawDbWeapon(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "year_id") val yearId: Long?,
    @ColumnInfo(name = "manufacturer_id") val manufacturerId: Long?,
    @ColumnInfo(name = "country_id") val countryId: Long?,
    @ColumnInfo(name = "type_id") val typeId: Long,
    @ColumnInfo(name = "length_mm") val lengthInMm: Int?,
    @ColumnInfo(name = "mass_kg") val massInKg: Float?,
    @ColumnInfo(name = "calibre_id") val calibreId: Long?,
    @ColumnInfo(name = "capacity_rounds") val capacityInRounds: Int?,
    @ColumnInfo(name = "rate_of_fire_rpm") val rateOfFireInRpm: Int?,
    @ColumnInfo(name = "effective_range_m") val effectiveRangeInM: Int?
)
