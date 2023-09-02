package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Embedded
import androidx.room.Relation

internal data class DbWeapon(
    @Embedded val weapon: RawDbWeapon,
    @Relation(entity = DbYear::class, parentColumn = "year_id", entityColumn = "id")
    val year: DbYear?,
    @Relation(entity = DbMake::class, parentColumn = "make_id", entityColumn = "id")
    val make: DbMake?,
    @Relation(entity = DbCountry::class, parentColumn = "country_id", entityColumn = "id")
    val country: DbCountry?,
    @Relation(entity = DbWeaponType::class, parentColumn = "type_id", entityColumn = "id")
    val type: DbWeaponType,
    @Relation(entity = DbCalibre::class, parentColumn = "calibre_id", entityColumn = "id")
    val calibre: DbCalibre?
)
