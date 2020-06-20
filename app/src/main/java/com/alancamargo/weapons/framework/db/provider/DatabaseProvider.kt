package com.alancamargo.weapons.framework.db.provider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.db.WeaponTypeDao
import com.alancamargo.weapons.framework.model.entities.DbCalibre
import com.alancamargo.weapons.framework.model.entities.DbCountry
import com.alancamargo.weapons.framework.model.entities.DbWeapon
import com.alancamargo.weapons.framework.model.entities.DbWeaponType

@Database(
    entities = [DbWeapon::class, DbWeaponType::class, DbCalibre::class, DbCountry::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseProvider : RoomDatabase() {

    abstract fun provideWeaponDao(): WeaponDao

    abstract fun provideWeaponTypeDao(): WeaponTypeDao

    abstract fun provideCalibreDao(): CalibreDao

    abstract fun provideCountryDao(): CountryDao

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
                    //.createFromAsset("database.db") TODO
                    .build()
            }

            return instance!!
        }

    }

}