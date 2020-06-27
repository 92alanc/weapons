package com.alancamargo.weapons.framework.db.provider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        private const val DB_NAME = "weapons-database"

        private var instance: DatabaseProvider? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): DatabaseProvider {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    DatabaseProvider::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .createFromAsset("database.db")
                    .build()
            }

            return instance!!
        }

    }

}