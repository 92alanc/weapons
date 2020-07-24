package com.alancamargo.weapons.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.adapter.CountryAdapter
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.ui.queries.WeaponQuery
import com.alancamargo.weapons.ui.tools.loadAds
import com.alancamargo.weapons.ui.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.activity_country_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CountryListActivity : AppCompatActivity(R.layout.activity_country_list),
    CountryAdapter.OnItemClickListener {

    private val viewModel by viewModel<CountryViewModel>()
    private val adapter by inject<CountryAdapter> { parametersOf(this) }

    private var state: CountryViewModel.State? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = adapter
        state = savedInstanceState?.getParcelable(KEY_STATE)
        state?.let(::processState) ?: fetchData()
        adView.loadAds()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    override fun onItemClick(country: UiCountry) {
        val query = WeaponQuery.ByCountry(country.id)
        val intent = WeaponListActivity.getIntent(this, query)
        startActivity(intent)
    }

    private fun fetchData() {
        viewModel.getState().observe(this, Observer {
            state = it
            processState(it)
        })
    }

    private fun processState(state: CountryViewModel.State) {
        when (state) {
            is CountryViewModel.State.Ready -> displayCountries(state.countries)
            is CountryViewModel.State.Loading -> showLoading()
            is CountryViewModel.State.Error -> showError()
        }
    }

    private fun displayCountries(countries: List<UiCountry>) {
        groupError.isVisible = false
        adapter.setData(countries)
        progressBar.isVisible = false
    }

    private fun showLoading() {
        groupError.isVisible = false
        progressBar.isVisible = true
    }

    private fun showError() {
        progressBar.isVisible = false
        groupError.isVisible = true
    }

    private companion object {
        const val KEY_STATE = "state"
    }

}