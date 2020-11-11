package com.alancamargo.weapons.data.di

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.data.repository.weapon.WeaponRepositoryImpl
import com.alancamargo.weapons.di.*
import com.alancamargo.weapons.framework.crash.CrashReportHelperImpl
import com.alancamargo.weapons.framework.local.*
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataModule : LayerModule() {

    override val module: Module = module {
        weaponRepository()
        localDataSources()
        helpers()
    }

    private fun Module.weaponRepository() {
        factory<WeaponRepository> {
            WeaponRepositoryImpl(
                localDataSource = get(),
                ioHelper = get()
            )
        }
    }

    private fun Module.localDataSources() {
        weaponLocalDataSource()
        weaponTypeLocalDataSource()
        countryLocalDataSource()
        calibreLocalDataSource()
        manufacturerLocalDataSource()
        yearLocalDataSource()
    }

    private fun Module.helpers() {
        factory { IoHelper(crashReportHelper = get()) }
        factory<CrashReportHelper> { CrashReportHelperImpl() }
    }

    // region Local data sources
    private fun Module.weaponLocalDataSource() {
        factory<WeaponLocalDataSource> {
            WeaponLocalDataSourceImpl(
                weaponDao = get(),
                weaponTypeLocalDataSource = get(),
                countryLocalDataSource = get(),
                calibreLocalDataSource = get(),
                manufacturerLocalDataSource = get(),
                yearLocalDataSource = get(),
                fileHelper = get()
            )
        }
    }

    private fun Module.weaponTypeLocalDataSource() {
        factory<WeaponTypeLocalDataSource> {
            WeaponTypeLocalDataSourceImpl(
                weaponTypeDao = get(),
                mapper = get(named(DB_WEAPON_TYPE_MAPPER))
            )
        }
    }

    private fun Module.countryLocalDataSource() {
        factory<CountryLocalDataSource> {
            CountryLocalDataSourceImpl(
                countryDao = get(),
                mapper = get(named(DB_COUNTRY_MAPPER))
            )
        }
    }

    private fun Module.calibreLocalDataSource() {
        factory<CalibreLocalDataSource> {
            CalibreLocalDataSourceImpl(
                calibreDao = get(),
                mapper = get(named(DB_CALIBRE_MAPPER))
            )
        }
    }

    private fun Module.manufacturerLocalDataSource() {
        factory<ManufacturerLocalDataSource> {
            ManufacturerLocalDataSourceImpl(
                manufacturerDao = get(),
                mapper = get(named(DB_MANUFACTURER_MAPPER))
            )
        }
    }

    private fun Module.yearLocalDataSource() {
        factory<YearLocalDataSource> {
            YearLocalDataSourceImpl(
                yearDao = get(),
                mapper = get(named(DB_YEAR_MAPPER))
            )
        }
    }
    // endregion

}