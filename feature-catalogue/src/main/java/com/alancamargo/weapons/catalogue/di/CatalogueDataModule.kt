package com.alancamargo.weapons.catalogue.di

import android.content.Context
import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import com.alancamargo.weapons.catalogue.data.db.WeaponDatabase
import com.alancamargo.weapons.catalogue.data.tools.FileHelper
import com.alancamargo.weapons.catalogue.data.tools.FileHelperImpl
import com.alancamargo.weapons.core.database.DatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideFileHelper(
        @ApplicationContext context: Context
    ): FileHelper = FileHelperImpl(context)
}
