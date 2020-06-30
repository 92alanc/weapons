package com.alancamargo.weapons.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.WeaponAdapter
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.tools.hide
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.tools.show
import com.alancamargo.weapons.ui.viewmodel.WeaponViewModel
import kotlinx.android.synthetic.main.activity_weapon_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WeaponListActivity : AppCompatActivity(R.layout.activity_weapon_list),
    WeaponAdapter.OnItemClickListener {

    private val viewModel by viewModel<WeaponViewModel>()
    private val query by lazy { intent.getParcelableExtra<WeaponQuery>(EXTRA_QUERY) }
    private val adapter by inject<WeaponAdapter> { parametersOf(this) }

    private var state: WeaponViewModel.State? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        state = savedInstanceState?.getParcelable(KEY_STATE)
        state?.let(::processState) ?: fetchData()
        adView.loadAds()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        state?.let { outState.putParcelable(KEY_STATE, it) }
    }

    override fun onItemClick(weapon: UiWeapon) {
        val intent = WeaponDetailsActivity.getIntent(this, weapon)
        startActivity(intent)
    }

    private fun fetchData() {
        viewModel.start(query)
        observeState()
    }

    private fun observeState() {
        viewModel.getState().observe(this, Observer {
            state = it
            processState(it)
        })
    }

    private fun processState(state: WeaponViewModel.State) {
        when (state) {
            is WeaponViewModel.State.Ready -> displayWeapons(state.weapons)
            is WeaponViewModel.State.Error -> showError()
            is WeaponViewModel.State.Loading -> showLoading()
        }
    }

    private fun displayWeapons(weapons: List<UiWeapon>) {
        groupError.hide()
        adapter.setData(weapons)
        progressBar.hide()
    }

    private fun showError() {
        progressBar.hide()
        groupError.show()
    }

    private fun showLoading() {
        groupError.hide()
        progressBar.show()
    }

    companion object {
        private const val EXTRA_QUERY = "query"
        private const val KEY_STATE = "state"

        fun getIntent(context: Context, query: WeaponQuery): Intent {
            return Intent(context, WeaponListActivity::class.java).putExtra(EXTRA_QUERY, query)
        }
    }

}