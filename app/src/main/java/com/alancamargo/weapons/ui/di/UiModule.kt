package com.alancamargo.weapons.ui.di

import coil.ImageLoader
import coil.request.CachePolicy
import com.alancamargo.weapons.R
import com.alancamargo.weapons.di.*
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.ui.entities.*
import com.alancamargo.weapons.ui.mappers.*
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigation
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigationImpl
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigation
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigationImpl
import com.alancamargo.weapons.ui.tools.AdLoader
import com.alancamargo.weapons.ui.tools.AdLoaderImpl
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.ResourcesHelperImpl
import com.alancamargo.weapons.ui.viewmodel.QueryViewModel
import com.alancamargo.weapons.ui.viewmodel.WeaponViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object UiModule : LayerModule() {

    override val module: Module = module {
        viewModels()
        navigation()
        adapters()
        imageLoader()
        adLoader()
        resourcesHelper()
        mappers()
    }

    private fun Module.viewModels() {
        weaponViewModel()
        queryViewModel()
    }

    private fun Module.mappers() {
        uiCountryMapper()
        uiCalibreMapper()
        uiManufacturerMapper()
        uiYearMapper()
        uiWeaponTypeMapper()
        uiWeaponMapper()
    }

    private fun Module.navigation() {
        factory<WeaponListActivityNavigation> { WeaponListActivityNavigationImpl() }
        factory<WeaponDetailsActivityNavigation> { WeaponDetailsActivityNavigationImpl() }
    }

    private fun Module.adapters() {
        factory { (onItemClickListener: OnItemClickListener) ->
            WeaponAdapter(
                resourcesHelper = get(),
                onItemClickListener = onItemClickListener,
                imageLoader = get()
            )
        }
        factory { (onItemClickListener: OnItemClickListener) ->
            WeaponListWithHeaderAdapter(
                onItemClickListener = onItemClickListener,
                imageLoader = get(),
                resourcesHelper = get()
            )
        }
    }

    private fun Module.imageLoader() {
        single {
            ImageLoader.Builder(androidContext())
                .crossfade(true)
                .error(R.drawable.ic_placeholder)
                .fallback(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_placeholder)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build()
        }
    }

    private fun Module.adLoader() {
        factory<AdLoader> { AdLoaderImpl() }
    }

    private fun Module.resourcesHelper() {
        factory<ResourcesHelper> {
            ResourcesHelperImpl(
                context = androidContext(),
                crashReportHelper = get()
            )
        }
    }

    // region View models
    private fun Module.weaponViewModel() {
        viewModel {
            WeaponViewModel(
                repository = get(),
                weaponMapper = get(named(UI_WEAPON_MAPPER))
            )
        }
    }

    private fun Module.queryViewModel() {
        viewModel { QueryViewModel() }
    }
    // endregion

    // region Mappers
    private fun Module.uiCountryMapper() {
        factory<EntityMapper<Country, UiCountry>>(named(UI_COUNTRY_MAPPER)) { UiCountryMapper() }
    }

    private fun Module.uiCalibreMapper() {
        factory<EntityMapper<Calibre, UiCalibre>>(named(UI_CALIBRE_MAPPER)) { UiCalibreMapper() }
    }

    private fun Module.uiManufacturerMapper() {
        factory<EntityMapper<Manufacturer, UiManufacturer>>(named(UI_MANUFACTURER_MAPPER)) {
            UiManufacturerMapper()
        }
    }

    private fun Module.uiYearMapper() {
        factory<EntityMapper<Year, UiYear>>(named(UI_YEAR_MAPPER)) { UiYearMapper() }
    }

    private fun Module.uiWeaponTypeMapper() {
        factory<EntityMapper<WeaponType, UiWeaponType>>(named(UI_WEAPON_TYPE_MAPPER)) {
            UiWeaponTypeMapper(androidContext())
        }
    }

    private fun Module.uiWeaponMapper() {
        factory<EntityMapper<Weapon, UiWeapon>>(named(UI_WEAPON_MAPPER)) {
            UiWeaponMapper(
                yearMapper = get(named(UI_YEAR_MAPPER)),
                manufacturerMapper = get(named(UI_MANUFACTURER_MAPPER)),
                countryMapper = get(named(UI_COUNTRY_MAPPER)),
                weaponTypeMapper = get(named(UI_WEAPON_TYPE_MAPPER)),
                calibreMapper = get(named(UI_CALIBRE_MAPPER))
            )
        }
    }
    // endregion

}