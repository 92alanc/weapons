package com.alancamargo.weapons.di

import androidx.room.Room
import coil.ImageLoaderBuilder
import coil.request.CachePolicy
import com.alancamargo.weapons.R
import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.data.repository.weapon.WeaponRepositoryImpl
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.crash.CrashReportHelperImpl
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import com.alancamargo.weapons.framework.entities.*
import com.alancamargo.weapons.framework.local.*
import com.alancamargo.weapons.framework.mappers.*
import com.alancamargo.weapons.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigation
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigationImpl
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigation
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigationImpl
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.ResourcesHelperImpl
import com.alancamargo.weapons.ui.viewmodel.QueryViewModel
import com.alancamargo.weapons.ui.viewmodel.WeaponViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DB_CALIBRE_MAPPER = "DB_CALIBRE_MAPPER"
private const val DB_COUNTRY_MAPPER = "DB_COUNTRY_MAPPER"
private const val DB_MANUFACTURER_MAPPER = "DB_MANUFACTURER_MAPPER"
private const val DB_YEAR_MAPPER = "DB_YEAR_MAPPER"
private const val DB_WEAPON_TYPE_MAPPER = "DB_WEAPON_TYPE_MAPPER"
const val DB_WEAPON_MAPPER = "DB_WEAPON_MAPPER"

fun getModules() = listOf(data, framework, ui)

private val data = module {
    factory<WeaponRepository> { WeaponRepositoryImpl(get(), get()) }

    factory<WeaponLocalDataSource> {
        WeaponLocalDataSourceImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    factory<WeaponTypeLocalDataSource> {
        WeaponTypeLocalDataSourceImpl(get(), get(named(DB_WEAPON_TYPE_MAPPER)))
    }
    factory<CountryLocalDataSource> {
        CountryLocalDataSourceImpl(get(), get(named(DB_COUNTRY_MAPPER)))
    }
    factory<CalibreLocalDataSource> {
        CalibreLocalDataSourceImpl(get(), get(named(DB_CALIBRE_MAPPER)))
    }
    factory<EntityMapper<DbManufacturer, Manufacturer>>(named(DB_MANUFACTURER_MAPPER)) {
        DbManufacturerMapper()
    }
    factory<EntityMapper<DbYear, Year>>(named(DB_YEAR_MAPPER)) { DbYearMapper() }
    factory<EntityMapper<DbWeaponType, WeaponType>>(named(DB_WEAPON_TYPE_MAPPER)) {
        DbWeaponTypeMapper()
    }
    factory<EntityMapper<DbWeapon, Weapon>>(named(DB_WEAPON_MAPPER)) { (
                                                                           year: Year?,
                                                                           manufacturer: Manufacturer?,
                                                                           country: Country?,
                                                                           type: WeaponType,
                                                                           calibre: Calibre?
                                                                       ) ->
        DbWeaponMapper(year, manufacturer, country, type, calibre)
    }
    factory<ManufacturerLocalDataSource> {
        ManufacturerLocalDataSourceImpl(get(), get(named(DB_MANUFACTURER_MAPPER)))
    }
    factory<YearLocalDataSource> { YearLocalDataSourceImpl(get(), get(named(DB_YEAR_MAPPER))) }

    factory { IoHelper(get()) }
    factory<CrashReportHelper> { CrashReportHelperImpl() }
}

private val framework = module {
    factory { get<DatabaseProvider>().provideWeaponDao() }
    factory { get<DatabaseProvider>().provideWeaponTypeDao() }
    factory { get<DatabaseProvider>().provideCountryDao() }
    factory { get<DatabaseProvider>().provideCalibreDao() }
    factory { get<DatabaseProvider>().provideManufacturerDao() }
    factory { get<DatabaseProvider>().provideYearDao() }
    factory<EntityMapper<DbCalibre, Calibre>>(named(DB_CALIBRE_MAPPER)) {
        DbCalibreMapper()
    }
    factory<EntityMapper<DbCountry, Country>>(named(DB_COUNTRY_MAPPER)) {
        DbCountryMapper()
    }

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

private val ui = module {
    viewModel { WeaponViewModel(get(), androidContext()) }
    viewModel { QueryViewModel() }

    factory<WeaponListActivityNavigation> { WeaponListActivityNavigationImpl() }
    factory<WeaponDetailsActivityNavigation> { WeaponDetailsActivityNavigationImpl() }

    factory<ResourcesHelper> { ResourcesHelperImpl(androidContext(), get()) }
    factory { (onItemClickListener: OnItemClickListener) ->
        WeaponAdapter(get(), onItemClickListener, get())
    }
    factory { (onItemClickListener: OnItemClickListener) ->
        WeaponListWithHeaderAdapter(onItemClickListener, get(), get())
    }

    single {
        ImageLoaderBuilder(androidContext())
            .crossfade(true)
            .error(R.drawable.ic_placeholder)
            .fallback(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    }
}