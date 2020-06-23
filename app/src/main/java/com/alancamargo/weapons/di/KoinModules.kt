package com.alancamargo.weapons.di

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.data.repository.WeaponRepository
import com.alancamargo.weapons.data.repository.WeaponRepositoryImpl
import com.alancamargo.weapons.framework.crash.CrashReportHelperImpl
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import com.alancamargo.weapons.framework.local.WeaponLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun getModules() = listOf(data, framework)

private val data = module {
    factory<WeaponRepository> { WeaponRepositoryImpl(get(), get()) }
    factory<WeaponLocalDataSource> { WeaponLocalDataSourceImpl(get(), get(), get(), get()) }
    factory { IoHelper(get()) }
    factory<CrashReportHelper> { CrashReportHelperImpl() }
}

private val framework = module {
    factory { DatabaseProvider.getInstance(androidContext()).provideWeaponDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideWeaponTypeDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideCountryDao() }
    factory { DatabaseProvider.getInstance(androidContext()).provideCalibreDao() }
}