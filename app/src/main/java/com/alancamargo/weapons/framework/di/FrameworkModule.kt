package com.alancamargo.weapons.framework.di

import androidx.room.Room
import com.alancamargo.weapons.catalogue.domain.model.Calibre
import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.catalogue.domain.model.Manufacturer
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.catalogue.domain.model.Year
import com.alancamargo.weapons.di.*
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.catalogue.data.db.WeaponDatabase
import com.alancamargo.weapons.catalogue.data.model.DbCalibre
import com.alancamargo.weapons.catalogue.data.model.DbCountry
import com.alancamargo.weapons.catalogue.data.model.DbManufacturer
import com.alancamargo.weapons.catalogue.data.model.DbWeapon
import com.alancamargo.weapons.catalogue.data.model.DbWeaponType
import com.alancamargo.weapons.catalogue.data.model.DbYear
import com.alancamargo.weapons.framework.model.*
import com.alancamargo.weapons.framework.mappers.*
import com.alancamargo.weapons.framework.tools.FileHelper
import com.alancamargo.weapons.framework.tools.FileHelperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

object FrameworkModule : LayerModule() {

    override val module: Module = module {
        databaseBuilder()
        dao()
        mappers()
        tools()
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
        factory { getDatabaseProvider().provideWeaponDao() }
        factory { getDatabaseProvider().provideWeaponTypeDao() }
        factory { getDatabaseProvider().provideCountryDao() }
        factory { getDatabaseProvider().provideCalibreDao() }
        factory { getDatabaseProvider().provideManufacturerDao() }
        factory { getDatabaseProvider().provideYearDao() }
    }

    private fun Module.mappers() {
        dbWeaponMapper()
        dbWeaponTypeMapper()
        dbYearMapper()
        dbManufacturerMapper()
        dbCalibreMapper()
        dbCountryMapper()
    }

    private fun Module.tools() {
        factory<FileHelper> {
            FileHelperImpl(
                context = androidContext(),
                countryDao = getDatabaseProvider().provideCountryDao()
            )
        }
    }

    private fun Scope.getDatabaseProvider() = get<WeaponDatabase>()

    // region Mappers
    private fun Module.dbWeaponMapper() {
        factory<EntityMapper<DbWeapon, Weapon>>(
            named(DB_WEAPON_MAPPER)
        ) { (params: DbWeaponMapper.Params) ->
            DbWeaponMapper(params)
        }
    }

    private fun Module.dbWeaponTypeMapper() {
        factory<EntityMapper<DbWeaponType, WeaponType>>(named(DB_WEAPON_TYPE_MAPPER)) {
            DbWeaponTypeMapper()
        }
    }

    private fun Module.dbYearMapper() {
        factory<EntityMapper<DbYear, Year>>(named(DB_YEAR_MAPPER)) { DbYearMapper() }
    }

    private fun Module.dbManufacturerMapper() {
        factory<EntityMapper<DbManufacturer, Manufacturer>>(named(DB_MANUFACTURER_MAPPER)) {
            DbManufacturerMapper()
        }
    }

    private fun Module.dbCalibreMapper() {
        factory<EntityMapper<DbCalibre, Calibre>>(named(DB_CALIBRE_MAPPER)) { DbCalibreMapper() }
    }

    private fun Module.dbCountryMapper() {
        factory<EntityMapper<DbCountry, Country>>(named(DB_COUNTRY_MAPPER)) { DbCountryMapper() }
    }
    // endregion

}