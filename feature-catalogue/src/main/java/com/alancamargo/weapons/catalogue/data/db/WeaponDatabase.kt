package com.alancamargo.weapons.catalogue.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.weapons.catalogue.data.model.DbCalibre
import com.alancamargo.weapons.catalogue.data.model.DbCountry
import com.alancamargo.weapons.catalogue.data.model.DbMake
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.DbYear
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon

@Database(
    entities = [
        RawDbWeapon::class,
        DbWeaponType::class,
        DbCalibre::class,
        DbCountry::class,
        DbMake::class,
        DbYear::class
    ],
    version = 4,
    exportSchema = false
)
internal abstract class WeaponDatabase : RoomDatabase() {

    abstract fun provideWeaponDao(): WeaponDao
}
