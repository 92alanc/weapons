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
import com.alancamargo.weapons.catalogue.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.catalogue.ui.viewmodel.WeaponViewModel
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.WeaponQuery
import com.alancamargo.weapons.core.ads.AdLoader
import com.alancamargo.weapons.core.extensions.args
import com.alancamargo.weapons.core.extensions.createIntent
import com.alancamargo.weapons.core.extensions.putArguments
import com.alancamargo.weapons.navigation.WeaponDetailsActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
internal class WeaponListActivity : AppCompatActivity(), OnItemClickListener {

    private var _binding: ActivityWeaponListBinding? = null

    private val binding: ActivityWeaponListBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<WeaponViewModel>()

    private val weaponAdapter by inject<WeaponAdapter> { parametersOf(this) }
    private val weaponListWithHeaderAdapter by inject<WeaponListWithHeaderAdapter> {
        parametersOf(this)
    }

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var weaponDetailsActivityNavigation: WeaponDetailsActivityNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWeaponListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fetchData()
        adLoader.loadBannerAds(binding.banner)
    }

    override fun onItemClick(weapon: UiWeapon) {
        weaponDetailsActivityNavigation.startActivity(this, weapon)
    }

    private fun fetchData() {
        observeState()
        viewModel.start(args.query)
    }

    private fun observeState() {
        viewModel.getState().observe(this) {
            processState(it)
        }
    }

    private fun processState(state: WeaponViewModel.State) {
        when (state) {
            is WeaponViewModel.State.WeaponListReady -> displayWeapons(state.weapons)
            is WeaponViewModel.State.WeaponListWithHeaderReady -> displayWeaponListWithHeader(
                state.weapons
            )
            is WeaponViewModel.State.Error -> showError()
            is WeaponViewModel.State.Loading -> showLoading()
            is WeaponViewModel.State.NoResults -> showNoResults()
        }
    }

    private fun displayWeapons(weapons: List<UiWeapon>) {
        binding.groupError.isVisible = false
        binding.groupNoResults.isVisible = false
        binding.recyclerView.adapter = weaponAdapter
        weaponAdapter.setData(weapons)
        binding.progressBar.isVisible = false
        val text = resources.getQuantityString(R.plurals.results_plural, weapons.size, weapons.size)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun displayWeaponListWithHeader(weapons: Map<UiWeaponListHeader?, List<UiWeapon>>) {
        with(binding) {
            groupError.isVisible = false
            groupNoResults.isVisible = false
            recyclerView.adapter = weaponListWithHeaderAdapter
            weaponListWithHeaderAdapter.setData(weapons)
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
    data class Args(val query: WeaponQuery) : Parcelable

    companion object {
        fun getIntent(context: Context, query: WeaponQuery): Intent {
            val args = Args(query)
            return context.createIntent<WeaponListActivity>().putArguments(args)
        }
    }
}
