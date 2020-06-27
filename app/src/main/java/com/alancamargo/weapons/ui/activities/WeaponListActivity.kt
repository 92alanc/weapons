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
import com.alancamargo.weapons.ui.viewmodel.WeaponListViewModel
import kotlinx.android.synthetic.main.activity_weapon_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WeaponListActivity : AppCompatActivity(R.layout.activity_weapon_list),
    WeaponAdapter.OnItemClickListener {

    private val viewModel by viewModel<WeaponListViewModel>()
    private val query by lazy { intent.getParcelableExtra<WeaponQuery>(EXTRA_QUERY) }
    private val adapter by inject<WeaponAdapter> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start(query)
        recyclerView.adapter = adapter
        observeCommand()
        adView.loadAds()
    }

    override fun onItemClick(weapon: UiWeapon) {
        // TODO
    }

    private fun observeCommand() {
        viewModel.getCommand().observe(this, Observer {
            processCommand(it)
        })
    }

    private fun processCommand(command: WeaponListViewModel.Command?) {
        when (command) {
            is WeaponListViewModel.Command.Display -> displayWeapons(command.weapons)
            is WeaponListViewModel.Command.Error -> showError()
            is WeaponListViewModel.Command.Load -> showLoading()
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

        fun getIntent(context: Context, query: WeaponQuery): Intent {
            return Intent(context, WeaponListActivity::class.java).putExtra(EXTRA_QUERY, query)
        }
    }

}