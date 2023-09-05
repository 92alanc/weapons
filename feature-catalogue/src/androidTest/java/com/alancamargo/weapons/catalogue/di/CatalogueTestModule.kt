package com.alancamargo.weapons.catalogue.di

import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import com.alancamargo.weapons.catalogue.data.tools.FileHelper
import com.alancamargo.weapons.catalogue.testtools.stubDbWeaponList
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.analytics.AnalyticsManager
import com.alancamargo.weapons.core.database.DatabaseProvider
import com.alancamargo.weapons.core.di.CoreModule
import com.alancamargo.weapons.core.di.CoreToolsModule
import com.alancamargo.weapons.core.log.Logger
import com.alancamargo.weapons.core.resources.ResourcesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        CoreModule::class,
        CoreToolsModule::class,
        CatalogueDataModule::class
    ]
)
internal object CatalogueTestModule {

    @Provides
    @Singleton
    fun provideMockResourcesHelper(): ResourcesHelper = mockk {
        every { getString(R.string.type_melee) } returns "Melee"
        every {
            getFormattedString(
                stringId = any(),
                arg = any()
            )
        } returns "Formatted string"
    }

    @Provides
    @Singleton
    fun provideMockAdLoader(): AdLoader = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockWeaponDao(): WeaponDao = mockk {
        coEvery { getAllWeapons() } returns stubDbWeaponList()
    }

    @Provides
    @Singleton
    fun provideMockFileHelper(): FileHelper = mockk {
        coEvery {
            getImageFilePaths(weaponName = any())
        } returns listOf("1.jpg", "2.jpg", "3.jpg")
    }

    @Provides
    @Singleton
    fun provideMockDatabaseProvider(): DatabaseProvider = mockk()

    @Provides
    @Singleton
    fun provideMockAnalyticsManager(): AnalyticsManager = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockLogger(): Logger = mockk(relaxed = true)
}
