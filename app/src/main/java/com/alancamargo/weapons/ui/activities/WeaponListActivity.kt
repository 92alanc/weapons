package com.alancamargo.weapons.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.weapons.R
import com.alancamargo.weapons.databinding.ActivityWeaponListBinding
import com.alancamargo.weapons.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.adapter.WeaponListWithHeaderAdapter
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.UiWeaponListHeader
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigation
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.tools.AdLoader
import com.alancamargo.weapons.ui.viewmodel.WeaponViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WeaponListActivity : AppCompatActivity(), OnItemClickListener {

    private val viewModel by viewModel<WeaponViewModel>()
    private val weaponDetailsActivityNavigation by inject<WeaponDetailsActivityNavigation>()
    private val adLoader by inject<AdLoader>()
    private val query by lazy { intent.getParcelableExtra<WeaponQuery>(EXTRA_QUERY) }

    private val weaponAdapter by inject<WeaponAdapter> { parametersOf(this) }
    private val weaponListWithHeaderAdapter by inject<WeaponListWithHeaderAdapter> {
        parametersOf(this)
    }

    private lateinit var binding: ActivityWeaponListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeaponListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fetchData()
        adLoader.loadBannerAds(binding.banner, R.string.banner_weapon_list)
    }

    override fun onItemClick(weapon: UiWeapon) {
        weaponDetailsActivityNavigation.startActivity(this, weapon)
    }

    private fun fetchData() {
        query?.let(viewModel::start)
        observeState()
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

    companion object {
        private const val EXTRA_QUERY = "query"

        fun getIntent(context: Context, query: WeaponQuery): Intent {
            return Intent(context, WeaponListActivity::class.java).putExtra(EXTRA_QUERY, query)
        }
    }

}