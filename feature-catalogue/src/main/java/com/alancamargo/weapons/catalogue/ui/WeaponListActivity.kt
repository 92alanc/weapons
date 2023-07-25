package com.alancamargo.weapons.catalogue.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.databinding.ActivityWeaponListBinding
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.catalogue.ui.viewmodel.WeaponListViewAction
import com.alancamargo.weapons.catalogue.ui.viewmodel.WeaponListViewModel
import com.alancamargo.weapons.catalogue.ui.viewmodel.WeaponListViewState
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
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

    private fun setUpUi() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adLoader.loadBannerAds(binding.banner)
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: WeaponListViewState) {
        // TODO
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

    private fun processState(state: WeaponListViewModel.State) {
        when (state) {
            is WeaponListViewModel.State.WeaponListReady -> displayWeapons(state.weapons)
            is WeaponListViewModel.State.WeaponListWithHeaderReady -> displayWeaponListWithHeader(
                state.weapons
            )
            is WeaponListViewModel.State.Error -> showError()
            is WeaponListViewModel.State.Loading -> showLoading()
            is WeaponListViewModel.State.NoResults -> showNoResults()
        }
    }

    private fun displayWeapons(weapons: List<UiWeapon>) {
        binding.groupError.isVisible = false
        binding.groupNoResults.isVisible = false
        binding.recyclerView.adapter = weaponAdapter
        weaponAdapter.submitList(weapons)
        binding.progressBar.isVisible = false
        val text = resources.getQuantityString(R.plurals.results_plural, weapons.size, weapons.size)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun displayWeaponListWithHeader(weapons: Map<UiWeaponListHeader?, List<UiWeapon>>) {
        with(binding) {
            groupError.isVisible = false
            groupNoResults.isVisible = false
            recyclerView.adapter = weaponListWithHeaderAdapter
            weaponListWithHeaderAdapter.submitList(weapons.entries.toList())
            progressBar.isVisible = false
        }
    }

    private fun showError() = with(binding) {
        progressBar.isVisible = false
        groupNoResults.isVisible = false
        groupError.isVisible = true
    }

    private fun showLoading() = with(binding) {
        groupError.isVisible = false
        groupNoResults.isVisible = false
        progressBar.isVisible = true
    }

    private fun showNoResults() = with(binding) {
        progressBar.isVisible = false
        groupError.isVisible = false
        groupNoResults.isVisible = true
    }

    @Parcelize
    data class Args(val query: UiWeaponQuery) : Parcelable

    companion object {
        fun getIntent(context: Context, query: UiWeaponQuery): Intent {
            val args = Args(query)
            return context.createIntent<WeaponListActivity>().putArguments(args)
        }
    }
}
