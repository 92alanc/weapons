package com.alancamargo.weapons.di

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.data.repository.calibre.CalibreRepository
import com.alancamargo.weapons.data.repository.calibre.CalibreRepositoryImpl
import com.alancamargo.weapons.data.repository.country.CountryRepository
import com.alancamargo.weapons.data.repository.country.CountryRepositoryImpl
import com.alancamargo.weapons.data.repository.manufacturer.ManufacturerRepository
import com.alancamargo.weapons.data.repository.manufacturer.ManufacturerRepositoryImpl
import com.alancamargo.weapons.data.repository.type.WeaponTypeRepository
import com.alancamargo.weapons.data.repository.type.WeaponTypeRepositoryImpl
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.data.repository.weapon.WeaponRepositoryImpl
import com.alancamargo.weapons.framework.crash.CrashReportHelperImpl
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import com.alancamargo.weapons.framework.local.*
import com.alancamargo.weapons.ui.viewmodel.WeaponListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getModules() = listOf(data, framework, ui)

private val data = module {
    factory<WeaponRepository> { WeaponRepositoryImpl(get(), get()) }
    factory<WeaponTypeRepository> { WeaponTypeRepositoryImpl(get(), get()) }
    factory<CountryRepository> { CountryRepositoryImpl(get(), get()) }
    factory<CalibreRepository> { CalibreRepositoryImpl(get(), get()) }
    factory<ManufacturerRepository> { ManufacturerRepositoryImpl(get(), get()) }

    factory<WeaponLocalDataSource> {
        WeaponLocalDataSourceImpl(get(), get(), get(), get(), get(), get())
    }
    factory<WeaponTypeLocalDataSource> { WeaponTypeLocalDataSourceImpl(get()) }
    factory<CountryLocalDataSource> { CountryLocalDataSourceImpl(get()) }
    factory<CalibreLocalDataSource> { CalibreLocalDataSourceImpl(get()) }
    factory<ManufacturerLocalDataSource> { ManufacturerLocalDataSourceImpl(get()) }
    factory<YearLocalDataSource> { YearLocalDataSourceImpl(get()) }

    factory { IoHelper(get()) }
    factory<CrashReportHelper> { CrashReportHelperImpl() }
}

private val framework = module {
    factory { DatabaseProvider.getInstance(androidContext()).provideWeaponDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideWeaponTypeDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideCountryDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideCalibreDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideManufacturerDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideYearDao() }
}

private val ui = module {
    viewModel { WeaponListViewModel(get()) }
}