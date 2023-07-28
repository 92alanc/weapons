package com.alancamargo.weapons.catalogue.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.weapons.catalogue.databinding.ActivityWeaponListBinding
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist.WeaponListViewAction
import com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist.WeaponListViewModel
import com.alancamargo.weapons.catalogue.ui.viewmodel.weaponlist.WeaponListViewState
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
import com.alancamargo.weapons.core.extensions.observeFlow
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.navigation.WeaponDetailsActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
internal class WeaponListActivity : AppCompatActivity() {

    private var _binding: ActivityWeaponListBinding? = null

    private val binding: ActivityWeaponListBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<WeaponListViewModel>()

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var weaponDetailsActivityNavigation: WeaponDetailsActivityNavigation

    @Inject
    lateinit var resourcesHelper: ResourcesHelper

    private val weaponAdapter by lazy {
        WeaponAdapter(
            resourcesHelper,
            viewModel::onWeaponClicked
        )
    }

    private val weaponListWithHeaderAdapter by lazy {
        WeaponListWithHeaderAdapter(
            resourcesHelper,
            viewModel::onWeaponClicked
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWeaponListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlows()
        viewModel.handleQuery(args.query)
    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.onBackClicked()
        return true
    }

    override fun onBackPressed() {
        viewModel.onNativeBackClicked()
    }

    private fun setUpUi() = with(binding) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adLoader.loadBannerAds(banner)
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: WeaponListViewState) = with(state) {
        binding.progressBar.isVisible = isLoading
        binding.groupError.isVisible = showError
        binding.groupNoResults.isVisible = showEmptyState

        weapons?.let {
            binding.recyclerView.adapter = weaponAdapter
            weaponAdapter.submitList(it)
        }

        weaponListWithHeader?.let {
            binding.recyclerView.adapter = weaponListWithHeaderAdapter
            weaponListWithHeaderAdapter.submitList(it)
        }
    }

    private fun onAction(action: WeaponListViewAction) = when (action) {
        is WeaponListViewAction.NavigateToWeaponDetails -> navigateToWeaponDetails(action.weapon)
        is WeaponListViewAction.Finish -> finish()
    }

    private fun navigateToWeaponDetails(weapon: UiWeapon) {
        weaponDetailsActivityNavigation.startActivity(
            context = this,
            weapon = weapon
        )
    }

    @Parcelize
    data class Args(val query: UiWeaponQuery) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent<WeaponListActivity>().putArguments(args)
        }
    }
}
