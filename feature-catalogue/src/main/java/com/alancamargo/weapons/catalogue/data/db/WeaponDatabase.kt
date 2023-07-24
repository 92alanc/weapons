package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.weapons.catalogue.data.model.DbCalibre
import com.alancamargo.weapons.catalogue.data.model.DbCountry
import com.alancamargo.weapons.catalogue.data.model.DbManufacturer
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.DbYear
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon

@Database(
    entities = [
        RawDbWeapon::class,
        DbWeaponType::class,
        DbCalibre::class,
        DbCountry::class,
        DbManufacturer::class,
        DbYear::class
    ],
    version = 2,
    exportSchema = false
)
internal abstract class WeaponDatabase : RoomDatabase() {

    abstract fun provideWeaponDao(): WeaponDao
}
