package com.alancamargo.weapons.framework.db.provider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.weapons.framework.db.*
import com.alancamargo.weapons.framework.entities.*

@Database(
    entities = [
        DbWeapon::class,
        DbWeaponType::class,
        DbCalibre::class,
        DbCountry::class,
        DbManufacturer::class,
        DbYear::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun provideWeaponDao(): WeaponDao

    abstract fun provideWeaponTypeDao(): WeaponTypeDao

    abstract fun provideCalibreDao(): CalibreDao

    abstract fun provideCountryDao(): CountryDao

    abstract fun provideManufacturerDao(): ManufacturerDao

    abstract fun provideYearDao(): YearDao

}