package com.alancamargo.weapons.framework.di

import androidx.room.Room
import com.alancamargo.weapons.di.DB_CALIBRE_MAPPER
import com.alancamargo.weapons.di.DB_COUNTRY_MAPPER
import com.alancamargo.weapons.di.LayerModule
import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import com.alancamargo.weapons.framework.entities.DbCalibre
import com.alancamargo.weapons.framework.entities.DbCountry
import com.alancamargo.weapons.framework.mappers.DbCalibreMapper
import com.alancamargo.weapons.framework.mappers.DbCountryMapper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object FrameworkModule : LayerModule() {

    override val module: Module = module {
        databaseBuilder()
        dao()
        mappers()
    }

    private fun Module.databaseBuilder() {
        single {
            Room.databaseBuilder(
                androidContext(),
                DatabaseProvider::class.java,
                "weapons-database"
            ).fallbackToDestructiveMigration()
                .createFromAsset("database.db")
                .build()
        }
    }

    private fun Module.dao() {
        factory { get<DatabaseProvider>().provideWeaponDao() }
        factory { get<DatabaseProvider>().provideWeaponTypeDao() }
        factory { get<DatabaseProvider>().provideCountryDao() }
        factory { get<DatabaseProvider>().provideCalibreDao() }
        factory { get<DatabaseProvider>().provideManufacturerDao() }
        factory { get<DatabaseProvider>().provideYearDao() }
    }

    private fun Module.mappers() {
        factory<EntityMapper<DbCalibre, Calibre>>(named(DB_CALIBRE_MAPPER)) { DbCalibreMapper() }
        factory<EntityMapper<DbCountry, Country>>(named(DB_COUNTRY_MAPPER)) { DbCountryMapper() }
    }

}