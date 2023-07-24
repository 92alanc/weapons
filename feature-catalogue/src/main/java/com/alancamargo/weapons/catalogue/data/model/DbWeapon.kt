package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Embedded
import androidx.room.Relation

internal data class DbWeapon(
    @Embedded val weapon: RawDbWeapon,
    @Relation(entity = DbYear::class, parentColumn = "yearId", entityColumn = "id")
    val year: DbYear?,
    @Relation(entity = DbManufacturer::class, parentColumn = "manufacturerId", entityColumn = "id")
    val manufacturer: DbManufacturer?,
    @Relation(entity = DbCountry::class, parentColumn = "countryId", entityColumn = "id")
    val country: DbCountry?,
    @Relation(entity = DbWeaponType::class, parentColumn = "typeId", entityColumn = "id")
    val type: DbWeaponType,
    @Relation(entity = DbCalibre::class, parentColumn = "calibreId", entityColumn = "id")
    val calibre: DbCalibre?
)
