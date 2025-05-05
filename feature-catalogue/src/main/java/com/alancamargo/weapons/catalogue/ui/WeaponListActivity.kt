package com.alancamargo.weapons.catalogue.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.alancamargo.weapons.catalogue.ui.fragments.WeaponDetailsBottomSheet
import com.alancamargo.weapons.catalogue.ui.view.WeaponListScreen
import com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist.WeaponListViewAction
import com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist.WeaponListViewModel
import com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist.WeaponListViewState
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.core.resources.ResourcesHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import com.alancamargo.weapons.core.design.R as CoreR

private const val TAG_WEAPON_DETAILS = "weapon-details"

@AndroidEntryPoint
internal class WeaponListActivity : AppCompatActivity() {

    private val args by args<Args>()
    private val viewModel by viewModels<WeaponListViewModel>()

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var resourcesHelper: ResourcesHelper

    private val weaponsState = mutableStateOf(emptyList<UiWeapon>())
    private val weaponListWithHeaderState = mutableStateOf(
        emptyList<Pair<UiWeaponListHeader?, List<UiWeapon>>>()
    )
    private val isProgressBarVisibleState = mutableStateOf(false)
    private val isWeaponListVisibleState = mutableStateOf(false)
    private val isWeaponListWithHeaderVisibleState = mutableStateOf(false)
    private val isEmptyStateVisibleState = mutableStateOf(false)
    private val isErrorVisibleState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeaponListScreen(
                weapons = weaponsState.value,
                weaponListWithHeader = weaponListWithHeaderState.value,
                isProgressBarVisible = isProgressBarVisibleState.value,
                isEmptyStateVisible = isEmptyStateVisibleState.value,
                isErrorVisible = isErrorVisibleState.value,
                resourcesHelper = resourcesHelper,
                adUnitId = stringResource(CoreR.string.banner_weapon_list),
                adLoader = adLoader,
                onItemClicked = viewModel::onWeaponClicked,
                onBackClicked = viewModel::onBackClicked
            )
        }
        observeViewModelFlows()
        viewModel.handleQuery(args.query)
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: WeaponListViewState) = with(state) {
        isProgressBarVisibleState.value = isLoading
        isWeaponListVisibleState.value = weapons != null
        isWeaponListWithHeaderVisibleState.value = weaponListWithHeader != null
        isEmptyStateVisibleState.value = showEmptyState
        isErrorVisibleState.value = showError

        weapons?.let { weaponsState.value = it }
        weaponListWithHeader?.let { weaponListWithHeaderState.value = it }
    }

    private fun onAction(action: WeaponListViewAction) = when (action) {
        is WeaponListViewAction.ShowWeaponDetails -> showWeaponDetails(action.weapon)
        is WeaponListViewAction.Finish -> finish()
    }

    private fun showWeaponDetails(weapon: UiWeapon) {
        val bottomSheet = WeaponDetailsBottomSheet.newInstance(weapon)
        bottomSheet.show(supportFragmentManager, TAG_WEAPON_DETAILS)
    }

    @Parcelize
    data class Args(val query: UiWeaponQuery) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent<WeaponListActivity>().putArguments(args)
        }
    }
}
