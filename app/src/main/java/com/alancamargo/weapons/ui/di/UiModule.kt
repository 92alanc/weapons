package com.alancamargo.weapons.ui.di

import coil.ImageLoaderBuilder
import coil.request.CachePolicy
import com.alancamargo.weapons.R
import com.alancamargo.weapons.di.LayerModule
import com.alancamargo.weapons.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.adapter.WeaponListWithHeaderAdapter
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
import org.koin.dsl.module

object UiModule : LayerModule() {

    override val module: Module = module {
        viewModels()
        navigation()
        adapters()
        imageLoader()
        adLoader()
        resourcesHelper()
    }

    private fun Module.viewModels() {
        weaponViewModel()
        queryViewModel()
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
                context = androidContext()
            )
        }
    }

    private fun Module.queryViewModel() {
        viewModel { QueryViewModel() }
    }
    // endregion

}