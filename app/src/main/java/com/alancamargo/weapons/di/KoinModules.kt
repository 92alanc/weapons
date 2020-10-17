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
import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.crash.CrashReportHelperImpl
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import com.alancamargo.weapons.framework.entities.DbCalibre
import com.alancamargo.weapons.framework.entities.DbCountry
import com.alancamargo.weapons.framework.local.*
import com.alancamargo.weapons.framework.mappers.calibre.CalibreToDbCalibreMapper
import com.alancamargo.weapons.framework.mappers.calibre.DbCalibreToCalibreMapper
import com.alancamargo.weapons.framework.mappers.country.CountryToDbCountryMapper
import com.alancamargo.weapons.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.ResourcesHelperImpl
import com.alancamargo.weapons.ui.viewmodel.QueryViewModel
import com.alancamargo.weapons.ui.viewmodel.WeaponViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DB_CALIBRE_MAPPER = "DB_CALIBRE_MAPPER"
private const val CALIBRE_MAPPER = "CALIBRE_MAPPER"
private const val COUNTRY_MAPPER = "COUNTRY_MAPPER"

fun getModules() = listOf(data, framework, ui)

private val data = module {
    factory<WeaponRepository> { WeaponRepositoryImpl(get(), get()) }

    factory<WeaponLocalDataSource> {
        WeaponLocalDataSourceImpl(get(), get(), get(), get(), get(), get())
    }
    factory<WeaponTypeLocalDataSource> { WeaponTypeLocalDataSourceImpl(get()) }
    factory<CountryLocalDataSource> { CountryLocalDataSourceImpl(get()) }
    factory<CalibreLocalDataSource> {
        CalibreLocalDataSourceImpl(get(), get(named(DB_CALIBRE_MAPPER)))
    }
    factory<ManufacturerLocalDataSource> { ManufacturerLocalDataSourceImpl(get()) }
    factory<YearLocalDataSource> { YearLocalDataSourceImpl(get()) }

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
    factory<EntityMapper<Calibre, DbCalibre>>(named(CALIBRE_MAPPER)) { CalibreToDbCalibreMapper() }
    factory<EntityMapper<DbCalibre, Calibre>>(named(DB_CALIBRE_MAPPER)) {
        DbCalibreToCalibreMapper()
    }
    factory<EntityMapper<Country, DbCountry>>(named(COUNTRY_MAPPER)) { CountryToDbCountryMapper() }

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