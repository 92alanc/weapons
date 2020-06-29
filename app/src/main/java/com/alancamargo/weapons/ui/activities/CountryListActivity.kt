package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.CountryAdapter
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.tools.hide
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.tools.show
import com.alancamargo.weapons.ui.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.activity_country_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CountryListActivity : AppCompatActivity(R.layout.activity_country_list),
    CountryAdapter.OnItemClickListener {

    private val viewModel by viewModel<CountryViewModel>()
    private val adapter by inject<CountryAdapter> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        observeState()
        adView.loadAds()
    }

    override fun onItemClick(country: UiCountry) {
        val query = WeaponQuery.ByCountry(country.id)
        val intent = WeaponListActivity.getIntent(this, query)
        startActivity(intent)
    }

    private fun observeState() {
        viewModel.getState().observe(this, Observer {
            processState(it)
        })
    }

    private fun processState(state: CountryViewModel.State?) {
        when (state) {
            is CountryViewModel.State.Ready -> displayCountries(state.countries)
            is CountryViewModel.State.Loading -> showLoading()
            is CountryViewModel.State.Error -> showError()
        }
    }

    private fun displayCountries(countries: List<UiCountry>) {
        groupError.hide()
        adapter.setData(countries)
        progressBar.hide()
    }

    private fun showLoading() {
        groupError.hide()
        progressBar.show()
    }

    private fun showError() {
        progressBar.hide()
        groupError.show()
    }

}