package com.alancamargo.weapons.di

import androidx.room.Room
import coil.ImageLoaderBuilder
import coil.request.CachePolicy
import com.alancamargo.weapons.R
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
import com.alancamargo.weapons.data.repository.year.YearRepository
import com.alancamargo.weapons.data.repository.year.YearRepositoryImpl
import com.alancamargo.weapons.framework.crash.CrashReportHelperImpl
import com.alancamargo.weapons.framework.db.provider.DatabaseProvider
import com.alancamargo.weapons.framework.local.*
import com.alancamargo.weapons.ui.adapter.CountryAdapter
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.ResourcesHelperImpl
import com.alancamargo.weapons.ui.viewmodel.*
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
    factory<YearRepository> { YearRepositoryImpl(get(), get()) }

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
    factory { get<DatabaseProvider>().provideWeaponDao() }
    factory { get<DatabaseProvider>().provideWeaponTypeDao() }
    factory { get<DatabaseProvider>().provideCountryDao() }
    factory { get<DatabaseProvider>().provideCalibreDao() }
    factory { get<DatabaseProvider>().provideManufacturerDao() }
    factory { get<DatabaseProvider>().provideYearDao() }

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
    viewModel { CountryViewModel(get()) }
    viewModel { CalibreViewModel(get()) }
    viewModel { YearViewModel(get()) }
    viewModel { WeaponTypeViewModel(get(), androidContext()) }
    viewModel { ManufacturerViewModel(get()) }

    factory<ResourcesHelper> { ResourcesHelperImpl(androidContext(), get()) }
    factory { (onItemClickListener: WeaponAdapter.OnItemClickListener) ->
        WeaponAdapter(get(), onItemClickListener, get())
    }
    factory { (onItemClickListener: CountryAdapter.OnItemClickListener) ->
        CountryAdapter(get(), onItemClickListener)
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