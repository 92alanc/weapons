package com.alancamargo.weapons.catalogue.di

import com.alancamargo.weapons.catalogue.data.db.CalibreDao
import com.alancamargo.weapons.catalogue.data.db.CountryDao
import com.alancamargo.weapons.catalogue.data.db.ManufacturerDao
import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import com.alancamargo.weapons.catalogue.data.db.WeaponDatabase
import com.alancamargo.weapons.catalogue.data.db.WeaponTypeDao
import com.alancamargo.weapons.catalogue.data.db.YearDao
import com.alancamargo.weapons.core.database.DatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CatalogueDataModule {

    @Provides
    @Singleton
    fun provideWeaponDatabase(
        provider: DatabaseProvider
    ): WeaponDatabase {
        return provider.provideDatabaseFromAsset(
            clazz = WeaponDatabase::class,
            name = "weapons-database",
            assetPath = "database.db"
        )
    }

    @Provides
    @Singleton
    fun provideWeaponDao(database: WeaponDatabase): WeaponDao {
        return database.provideWeaponDao()
    }

    @Provides
    @Singleton
    fun provideCalibreDao(database: WeaponDatabase): CalibreDao {
        return database.provideCalibreDao()
    }

    @Provides
    @Singleton
    fun provideCountryDao(database: WeaponDatabase): CountryDao {
        return database.provideCountryDao()
    }

    @Provides
    @Singleton
    fun provideManufacturerDao(database: WeaponDatabase): ManufacturerDao {
        return database.provideManufacturerDao()
    }

    @Provides
    @Singleton
    fun provideWeaponTypeDao(database: WeaponDatabase): WeaponTypeDao {
        return database.provideWeaponTypeDao()
    }

    @Provides
    @Singleton
    fun provideYearDao(database: WeaponDatabase): YearDao {
        return database.provideYearDao()
    }
}
